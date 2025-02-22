package thebottle.sock.block.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;

public class SockworkingTableScreenHandler extends ScreenHandler {
    private final ScreenHandlerContext context;
    private final Inventory inventory = new SimpleInventory(3);

    public SockworkingTableScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }

    public SockworkingTableScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(SockScreenhandlerTypes.SOCKWORKING_TABLE, syncId);
        this.context = context;

        //Wool
        this.addSlot(new Slot(this.inventory, 0, 0, 0){
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(ItemTags.WOOL);
            }
        });
        //Other item to craft with wool
        this.addSlot(new Slot(this.inventory, 1, 20, 0));
        //The outputted crafted item
        this.addSlot(new Slot(this.inventory, 2, 40, 0){
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });

        this.addPlayerSlots(playerInventory, 8, 84);


    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return null; //Todo
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
