package thebottle.sock;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.DataGenerator;
import thebottle.sock.datagen.providers.SockEquipmentAssetProvider;
import thebottle.sock.datagen.providers.languages.EnUSLangProvider;

public class SockDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(EnUSLangProvider::new);

		DataGenerator.Pack vanillaPack = fabricDataGenerator.createPack();
		vanillaPack.addProvider(SockEquipmentAssetProvider::new);
	}
}
