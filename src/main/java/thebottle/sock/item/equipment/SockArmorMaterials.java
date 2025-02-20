package thebottle.sock.item.equipment;

import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundEvents;

import java.util.Map;

public class SockArmorMaterials {
    public static ArmorMaterial SOCK = new ArmorMaterial(
            3,
            Map.of(EquipmentType.BOOTS, 1),
            50,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
            0,
            0,
            ItemTags.WOOL,
            SockEquipmentAssetKeys.SOCK
    );
}
