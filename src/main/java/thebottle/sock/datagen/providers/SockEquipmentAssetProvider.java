package thebottle.sock.datagen.providers;

import net.minecraft.client.data.EquipmentAssetProvider;
import net.minecraft.client.render.entity.equipment.EquipmentModel;
import net.minecraft.data.DataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataWriter;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.registry.RegistryKey;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static thebottle.sock.Util.of;

public class SockEquipmentAssetProvider extends EquipmentAssetProvider {
    protected final DataOutput.PathResolver pathResolver;

    public static final RegistryKey<EquipmentAsset> CARDBOARD = id("cardboard");

    public SockEquipmentAssetProvider(DataOutput output) {
        super(output);
        this.pathResolver = output.getResolver(DataOutput.OutputType.RESOURCE_PACK, "equipment");
    }

    @Override
    public CompletableFuture<?> run(DataWriter writer) {
        Map<RegistryKey<EquipmentAsset>, EquipmentModel> map = new HashMap<>();

        map.put(CARDBOARD, onlyHumanoidBody("cardboard"));

        return DataProvider.writeAllToPath(writer, EquipmentModel.CODEC, this.pathResolver::resolveJson, map);
    }

    private static EquipmentModel onlyHumanoidBody(String name) {
        return EquipmentModel.builder().addMainHumanoidLayer(of(name), false).build();
    }

    private static RegistryKey<EquipmentAsset> id(String name) {
        return RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, of(name));
    }
}
