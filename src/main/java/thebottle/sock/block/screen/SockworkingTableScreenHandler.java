package thebottle.sock.block.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import thebottle.sock.recipe.SockworkingRecipe;
import thebottle.sock.recipe.SockworkingRecipeInput;

public class SockworkingTableScreenHandler extends ScreenHandler {
    private final ScreenHandlerContext context;
    private final Inventory inventory = new SimpleInventory(2){
        @Override
        public void markDirty() {
            super.markDirty();
            SockworkingTableScreenHandler.this.onContentChanged(this);
        }
    };

    public SockworkingTableScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }

    public SockworkingTableScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(SockScreenhandlerTypes.SOCKWORKING_TABLE, syncId);
        this.context = context;

        //Wool
        this.addSlot(new Slot(this.inventory, 0, 0, 0));
        //Other item to craft with wool
        this.addSlot(new Slot(this.inventory, 1, 20, 0));
        this.addPlayerSlots(playerInventory, 8, 84);
    }

    public void tryCraft(ServerWorld world) {
        world.getRecipeManager().getFirstMatch(
                SockworkingRecipe.Type.INSTANCE,
                SockworkingRecipeInput.of(
                        this.inventory.getStack(0),
                        this.inventory.getStack(1)
                ),
                world
        ).ifPresent(
                recipeEntry -> {
                    ItemStack output = recipeEntry.value().getOutput();

                    this.inventory.removeStack(0, 1);
                    this.inventory.removeStack(1, 1);

                    this.setCursorStack(output);
                }
        );
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        if (player instanceof ServerPlayerEntity) {
            player.getInventory().offerOrDrop(this.inventory.getStack(0));
            player.getInventory().offerOrDrop(this.inventory.getStack(1));
            this.inventory.clear();
        }
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
