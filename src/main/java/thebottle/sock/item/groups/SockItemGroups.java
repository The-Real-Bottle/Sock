package thebottle.sock.item.groups;

import de.dafuqs.fractal.api.ItemSubGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import thebottle.sock.block.SockBlocks;
import thebottle.sock.item.SockItems;

import static thebottle.sock.Util.of;

public class SockItemGroups {
    public static final ItemGroup SOCKS = register(
            "socks",
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(SockItems.BLUE_SOCK))
                    .entries((displayContext, entries) -> {
                        entries.add(SockItems.BLUE_SOCK);
                        for (ItemSubGroup subGroup : SockItemGroups.SOCKS.fractal$getChildren()) {
                            entries.addAll(subGroup.getSearchTabStacks(), ItemGroup.StackVisibility.SEARCH_TAB_ONLY);
                        }
                    })
                    .displayName(Text.translatable("itemGroup.sock.socks"))
                    .noRenderedName()
                    .build()
    );

    public static final ItemGroup SOCKS_SOCKS = new ItemSubGroup.Builder(
            SOCKS,
            of("socks/socks"),
            Text.translatable("itemGroup.sock.socks.socks")
    )
            .entries((displayContext, entries) -> {
                entries.add(SockItems.BLUE_SOCK);
                entries.add(SockItems.GREEN_SOCK);
                entries.add(SockItems.VOID_SOCK);
            })
            .build();

    public static final ItemGroup SOCKS_EQUIPMENT = new ItemSubGroup.Builder(
            SOCKS,
            of("socks/equipment"),
            Text.translatable("itemGroup.sock.socks.equipment")
    )
            .entries((displayContext, entries) -> {
                entries.add(SockItems.H2O_SUIT);
                entries.add(SockItems.THE_BOTTLE_ITEM);
            })
            .build();

    public static final ItemGroup SOCKS_FUNCTIONAL = new ItemSubGroup.Builder(
            SOCKS,
            of("socks/functional"),
            Text.translatable("itemGroup.sock.socks.functional")
    )
            .entries((displayContext, entries) -> {
                entries.add(SockBlocks.SOCKWORKING_TABLE);
            })
            .build();

    private static ItemGroup register(String id, ItemGroup group) {
        return Registry.register(Registries.ITEM_GROUP, of(id), group);
    }

    public static void init() {
    }
}
