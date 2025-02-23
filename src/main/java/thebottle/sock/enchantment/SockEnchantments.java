package thebottle.sock.enchantment;

import com.mojang.serialization.MapCodec;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.*;
import thebottle.sock.datagen.providers.SockTagProviders;

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

    public static void bootstrap(Registerable<Enchantment> context) {
        registerEnchantment(context, SockEnchantments.WATERPROOF, Enchantment.builder(
                Enchantment.definition(
                        context.getRegistryLookup(RegistryKeys.ITEM).getOrThrow(SockTagProviders.SockItemTagProvider.SOCKS),
                        10,
                        5,
                        Enchantment.leveledCost(1, 10),
                        Enchantment.leveledCost(1, 15),
                        5,
                        AttributeModifierSlot.ANY
                )
        ));

        registerEnchantment(context, SockEnchantments.SPEEDY, Enchantment.builder(
                Enchantment.definition(
                        context.getRegistryLookup(RegistryKeys.ITEM).getOrThrow(SockTagProviders.SockItemTagProvider.SOCKS),
                        10,
                        3,
                        Enchantment.leveledCost(1, 10),
                        Enchantment.leveledCost(1, 15),
                        5,
                        AttributeModifierSlot.ANY
                )
        ));
    }

    private static void registerEnchantment(Registerable<Enchantment> context, RegistryKey<Enchantment> key, Enchantment.Builder builder) {
        context.register(key, builder.build(key.getValue()));
    }

    public static void init() {}
}
