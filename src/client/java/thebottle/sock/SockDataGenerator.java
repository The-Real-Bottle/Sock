package thebottle.sock;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.DataGenerator;
import thebottle.sock.datagen.providers.SockEquipmentAssetProvider;

public class SockDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		//Add datagens that don't need bootstaps using pack

		DataGenerator.Pack vanillaPack = fabricDataGenerator.createPack();
		vanillaPack.addProvider(SockEquipmentAssetProvider::new);
	}
}
