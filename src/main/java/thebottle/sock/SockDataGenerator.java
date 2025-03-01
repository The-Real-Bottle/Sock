package thebottle.sock;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.DataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import thebottle.sock.datagen.providers.*;
import thebottle.sock.datagen.providers.languages.EnUSLangProvider;
import thebottle.sock.enchantment.SockEnchantments;

@Environment(EnvType.CLIENT)
public class SockDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(EnUSLangProvider::new);
        pack.addProvider(SockModelProvider::new);
        pack.addProvider(SockTagProviders.SockItemTagProvider::new);
        pack.addProvider(SockTagProviders.SockBlockTagProvider::new);
        pack.addProvider(SockLootTableProviders.SockBlockLootTableProvider::new);
        pack.addProvider(SockRecipeProvider::new);
        pack.addProvider(SockEnchantmentProvider::new);
        pack.addProvider(SockTagProviders.SockEnchantmentTagProvider::new);

        DataGenerator.Pack vanillaPack = fabricDataGenerator.createPack();
        vanillaPack.addProvider(SockEquipmentAssetProvider::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.ENCHANTMENT, SockEnchantments::bootstrap);
    }
}
