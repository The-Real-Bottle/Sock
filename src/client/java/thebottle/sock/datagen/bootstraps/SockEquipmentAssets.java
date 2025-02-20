package thebottle.sock.datagen.bootstraps;

import net.minecraft.client.render.entity.equipment.EquipmentModel;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;

import static thebottle.sock.Util.of;

public final class SockEquipmentAssets {
    private static final RegistryKey<? extends Registry<EquipmentAsset>> ROOT_ID = RegistryKey.ofRegistry(Identifier.ofVanilla("equipment_asset"));

    public static final RegistryKey<EquipmentAsset> SOCK = id("sock");

    private static RegistryKey<EquipmentAsset> id(String name) {
        return RegistryKey.of(ROOT_ID, of(name));
    }

    public static void bootstrap(BiConsumer<RegistryKey<EquipmentAsset>, EquipmentModel> consumer) {
        consumer.accept(SOCK, onlyHumanoidBody("sock"));
    }

    private static EquipmentModel onlyHumanoidBody(String name) {
        return EquipmentModel.builder().addMainHumanoidLayer(of(name), false).build();
    }
}
