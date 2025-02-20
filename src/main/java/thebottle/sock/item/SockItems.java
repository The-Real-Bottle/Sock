package thebottle.sock.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import static thebottle.sock.Util.of;

public abstract class SockItems {
    public static final SockItem SOCK = register("sock", new SockItem(new Item.Settings()));

    private static <T extends Item> T register(String name, T item) {
        return Registry.register(Registries.ITEM, of(name), item);
    }

    public static void init() {}
}
