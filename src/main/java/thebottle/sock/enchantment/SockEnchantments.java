package thebottle.sock.enchantment;

import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import static thebottle.sock.Util.of;

public class SockEnchantments {
    public static final RegistryKey<Enchantment> WATERPROOF = registerEnchantmentKey("waterproof");
    public static final RegistryKey<Enchantment> SPEEDY = registerEnchantmentKey("speedy");

    private static RegistryKey<Enchantment> registerEnchantmentKey(String path) {
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, of(path));
    }

    private static <T extends EnchantmentEntityEffect> MapCodec<T> registerEffect(String id, MapCodec<T> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, of(id), codec);
    }

    public static void init() {}
}
