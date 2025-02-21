package thebottle.sock;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.DataGenerator;
import thebottle.sock.datagen.providers.SockEquipmentAssetProvider;
import thebottle.sock.datagen.providers.SockModelProvider;
import thebottle.sock.datagen.providers.SockTagProviders;
import thebottle.sock.datagen.providers.languages.EnUSLangProvider;

@Environment(EnvType.CLIENT)
public class SockDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(EnUSLangProvider::new);
		pack.addProvider(SockModelProvider::new);
		pack.addProvider(SockTagProviders.SockItemTagProvider::new);

		DataGenerator.Pack vanillaPack = fabricDataGenerator.createPack();
		vanillaPack.addProvider(SockEquipmentAssetProvider::new);
	}
}
