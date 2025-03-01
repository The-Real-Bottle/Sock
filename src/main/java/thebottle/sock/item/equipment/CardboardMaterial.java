package thebottle.sock.item.equipment;

import net.minecraft.item.Item;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvents;
import thebottle.sock.datagen.providers.SockEquipmentAssetProvider;
import thebottle.sock.datagen.providers.SockTagProviders;

import java.util.Map;

public class CardboardMaterial {
    public static final int BASE_DURABILITY = 8;
    public static final int ENCHANTABILITY = 17;
    public static final int TOUGHNESS = 1;
    public static final TagKey<Item> REPAIR_ITEM = SockTagProviders.SockItemTagProvider.SOCKS;

    public static final ArmorMaterial INSTANCE = new ArmorMaterial(
            BASE_DURABILITY,
            Map.of(
                    EquipmentType.CHESTPLATE, 4
            ),
            ENCHANTABILITY,
            SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
            TOUGHNESS,
            0,
            REPAIR_ITEM,
            SockEquipmentAssetProvider.CARDBOARD
    );
}
