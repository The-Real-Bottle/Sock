package thebottle.sock.datagen.providers;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import thebottle.sock.enchantment.SockEnchantments;

import java.util.concurrent.CompletableFuture;

public class SockEnchantmentProvider extends FabricDynamicRegistryProvider {
    public SockEnchantmentProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        register(entries, SockEnchantments.WATERPROOF, Enchantment.builder(
                Enchantment.definition(
                        registries.getOrThrow(RegistryKeys.ITEM).getOrThrow(SockTagProviders.SockItemTagProvider.SOCKS),
                        10,
                        5,
                        Enchantment.leveledCost(1, 10),
                        Enchantment.leveledCost(1, 15),
                        5,
                        AttributeModifierSlot.ANY
                )
        ));

        register(entries, SockEnchantments.SPEEDY, Enchantment.builder(
                Enchantment.definition(
                        registries.getOrThrow(RegistryKeys.ITEM).getOrThrow(SockTagProviders.SockItemTagProvider.SOCKS),
                        10,
                        3,
                        Enchantment.leveledCost(1, 10),
                        Enchantment.leveledCost(1, 15),
                        5,
                        AttributeModifierSlot.ANY
                )
        ));
    }

    private void register(Entries entries, RegistryKey<Enchantment> key, Enchantment.Builder builder, ResourceCondition... resourceConditions) {
        entries.add(key, builder.build(key.getValue()), resourceConditions);
    }

    @Override
    public String getName() {
        return "Sock Enchantments";
    }
}
