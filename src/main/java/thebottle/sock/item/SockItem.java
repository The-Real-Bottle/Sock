package thebottle.sock.item;

import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import thebottle.sock.item.equipment.SockArmorMaterials;

public class SockItem extends ArmorItem {
    public SockItem(Item.Settings settings) {
        super(SockArmorMaterials.SOCK, EquipmentType.BOOTS, settings);
    }
}
