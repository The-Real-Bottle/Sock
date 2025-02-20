package thebottle.sock.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import static thebottle.sock.Util.of;

public abstract class SockItems {
    private static Item register(String name, Item item) {
        return Registry.register(Registries.ITEM, of(name), item);
    }

    public static void init() {}
}
