package thebottle.sock.datagen.providers;

import net.minecraft.client.data.EquipmentAssetProvider;
import net.minecraft.client.render.entity.equipment.EquipmentModel;
import net.minecraft.data.DataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataWriter;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.registry.RegistryKey;
import thebottle.sock.datagen.bootstraps.SockEquipmentAssets;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class SockEquipmentAssetProvider extends EquipmentAssetProvider {
    protected final DataOutput.PathResolver pathResolver;

    public SockEquipmentAssetProvider(DataOutput output) {
        super(output);
        this.pathResolver = output.getResolver(DataOutput.OutputType.RESOURCE_PACK, "equipment");
    }

    @Override
    public CompletableFuture<?> run(DataWriter writer) {
        Map<RegistryKey<EquipmentAsset>, EquipmentModel> map = new HashMap<>();
        SockEquipmentAssets.bootstrap((key, model) -> {
            if (map.putIfAbsent(key, model) != null)
                throw new IllegalStateException("Duplicate equipment asset for id " + key.getValue().toString());
        });

        return DataProvider.writeAllToPath(writer, EquipmentModel.CODEC, this.pathResolver::resolveJson, map);
    }
}
