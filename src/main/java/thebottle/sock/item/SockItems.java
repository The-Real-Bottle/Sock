package thebottle.sock.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import static thebottle.sock.Util.of;

public abstract class SockItems {
    public static final SockItem SOCK = register(
            "sock",
            SockItem::new,
            new Item.Settings(),
            new SockItem.SockData(
                    "animated_leaf_core",
                    List.of()
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
    }

    //Extended register so we can still use ItemClass::new for items that need more than just settings
    //If you need more than 1 extra object of data idk use a record or something
    private static <T extends Item, S> T register(String name, BiFunction<Item.Settings, S, T> itemFunctionWithExtraData, Item.Settings settings, S extraData) {
        Identifier id = of(name);
        RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM, id);
        return Registry.register(
                Registries.ITEM,
                id,
                itemFunctionWithExtraData.apply(settings.registryKey(registryKey), extraData)
        );
    }

    public static void init() {
    }
}
