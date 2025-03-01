package thebottle.sock.item;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.function.TriFunction;
import thebottle.sock.block.SockBlocks;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import static thebottle.sock.Util.of;
import static thebottle.sock.block.SockBlocks.THE_BOTTLE_ID;

public abstract class SockItems {
    public static final SockItem BLUE_SOCK = registerSock(
            "blue",
            SockItem::new,
            new Item.Settings().enchantable(5),
            new SockItem.AttributeData(
                    EntityAttributes.MAX_HEALTH,
                    1,
                    EntityAttributeModifier.Operation.ADD_VALUE
            )
    );

    public static final SockItem GREEN_SOCK = registerSock(
            "green",
            SockItem::new,
            new Item.Settings().enchantable(3),
            new SockItem.AttributeData(
                    EntityAttributes.ATTACK_DAMAGE,
                    2,
                    EntityAttributeModifier.Operation.ADD_VALUE
            ),
            new SockItem.AttributeData(
                    EntityAttributes.ATTACK_SPEED,
                    0.2,
                    EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
            )
    );

    public static final SockItem VOID_SOCK = registerSock(
            "void",
            SockItem::new,
            new Item.Settings().enchantable(7),
            new SockItem.AttributeData(
                    EntityAttributes.GRAVITY,
                    -0.33,
                    EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
            ),
            new SockItem.AttributeData(
                    EntityAttributes.OXYGEN_BONUS,
                    2,
                    EntityAttributeModifier.Operation.ADD_VALUE
            ),
            new SockItem.AttributeData(
                    EntityAttributes.JUMP_STRENGTH,
                    0.1,
                    EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
            )
    );

    private static <T extends Item> T register(String name, Function<Item.Settings, T> itemFunction, Item.Settings settings) {
        Identifier id = of(name);
        RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM, id);
        return Registry.register(
                Registries.ITEM,
                id,
                itemFunction.apply(settings.registryKey(registryKey))
        );
    }    public static final BlockItem THE_BOTTLE_ITEM = register(THE_BOTTLE_ID, settings -> new TheBottleItem(SockBlocks.THE_BOTTLE, settings), new Item.Settings().useBlockPrefixedTranslationKey().recipeRemainder(SockItems.THE_BOTTLE_ITEM));

    //Extended register so we can still use ItemClass::new for items that need more than just settings
    //If you need more than 1 extra object of data IDK use a record or something
    private static <T extends Item, S> T register(String name, BiFunction<Item.Settings, S, T> itemFunctionWithExtraData, Item.Settings settings, S extraData) {
        Identifier id = of(name);
        RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM, id);
        return Registry.register(
                Registries.ITEM,
                id,
                itemFunctionWithExtraData.apply(settings.registryKey(registryKey), extraData)
        );
    }

    private static <T extends Item> T registerSock(String name, Function<Item.Settings, T> itemFunction, Item.Settings settings) {
        Identifier id = of(name);
        RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM, id);
        return Registry.register(
                Registries.ITEM,
                id,
                itemFunction.apply(settings.registryKey(registryKey))
        );
    }

    private static <T extends SockItem> T registerSock(String name, TriFunction<Item.Settings, String, List<SockItem.AttributeData>, T> itemFunction, Item.Settings settings, SockItem.AttributeData...
            attributes) {
        return registerSock(
                name + "_sock",
                (s) -> itemFunction.apply(s, name, List.of(attributes)), settings);
    }

    public static void init() {
    }



}
