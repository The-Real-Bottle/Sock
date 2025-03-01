package thebottle.sock.enchantment;

import com.mojang.serialization.MapCodec;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntryList;
import thebottle.sock.datagen.providers.SockTagProviders;
import thebottle.sock.item.SockItems;

import static thebottle.sock.Util.of;

public class SockEnchantments {
    public static final RegistryKey<Enchantment> WATERPROOF = registerEnchantmentKey("waterproof");
    public static final RegistryKey<Enchantment> SPEEDY = registerEnchantmentKey("speedy");
    public static final RegistryKey<Enchantment> GREATER_STEPPING = registerEnchantmentKey("greater_stepping");
    
    public static final RegistryKey<Enchantment> WATERFULL = registerEnchantmentKey("waterfull"); //Name can be improved probably

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

        registerEnchantment(context, SockEnchantments.GREATER_STEPPING, Enchantment.builder(
                Enchantment.definition(
                        context.getRegistryLookup(RegistryKeys.ITEM).getOrThrow(SockTagProviders.SockItemTagProvider.SOCKS),
                        1,
                        20,
                        Enchantment.leveledCost(25, 25),
                        Enchantment.leveledCost(75, 25),
                        4
                )
        ));

        registerEnchantment(context, SockEnchantments.WATERFULL, Enchantment.builder(
                Enchantment.definition(
                        RegistryEntryList.of(
                                Registries.ITEM.getEntry(SockItems.H2O_SUIT)
                        ),
                        3,
                        3,
                        Enchantment.leveledCost(1, 10),
                        Enchantment.leveledCost(1, 15),
                        3
                )
        ));
    }

    private static void registerEnchantment(Registerable<Enchantment> context, RegistryKey<Enchantment> key, Enchantment.Builder builder) {
        context.register(key, builder.build(key.getValue()));
    }

    public static void init() {
    }
}
