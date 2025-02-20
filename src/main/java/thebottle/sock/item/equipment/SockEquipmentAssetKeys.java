package thebottle.sock.item.equipment;

import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.registry.RegistryKey;
import static thebottle.sock.Util.of;

public abstract class SockEquipmentAssetKeys {
    public static final RegistryKey<EquipmentAsset> SOCK = register("sock");

    private static RegistryKey<EquipmentAsset> register(String name) {
        return RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, of(name));
    }

    public static void init() {}
}
