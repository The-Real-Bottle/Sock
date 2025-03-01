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
    private final Slot WOOL_SLOT;
    private final Slot OTHER_ITEM_SLOT;
    private final Slot OUTPUT_SLOT;
    private final Inventory outputInventory;
    private final Inventory inventory = new SimpleInventory(2) {
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

        this.outputInventory = new SimpleInventory(ItemStack.EMPTY);
        this.context.run((world, blockPos) -> {
            if (world instanceof ServerWorld serverWorld) {

                serverWorld.getRecipeManager().getFirstMatch(
                        SockworkingRecipe.Type.INSTANCE,
                        SockworkingRecipeInput.of(
                                this.inventory.getStack(0),
                                this.inventory.getStack(1)
                        ),
                        world
                ).ifPresent(recipeEntry -> this.outputInventory.setStack(0, recipeEntry.value().getOutput()));
            }
        });

        //Wool
        WOOL_SLOT = this.addSlot(new Slot(this.inventory, 0, 26, 17));

        //Other item to craft with wool
        OTHER_ITEM_SLOT = this.addSlot(new Slot(this.inventory, 1, 26, 40));

        OUTPUT_SLOT = this.addSlot(new Slot(this.outputInventory, 0, 100, 34) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }

            @Override
            public boolean canTakeItems(PlayerEntity playerEntity) {
                return false;
            }

            @Override
            public boolean canBeHighlighted() {
                return false;
            }
        });

        this.addPlayerSlots(playerInventory, 8, 84);
    }

    @Override
    public void onContentChanged(Inventory inventory) {
        this.context.run((world, blockPos) -> {
            if (world instanceof ServerWorld serverWorld) {
                serverWorld.getRecipeManager().getFirstMatch(
                        SockworkingRecipe.Type.INSTANCE,
                        SockworkingRecipeInput.of(
                                inventory.getStack(0),
                                inventory.getStack(1)
                        ),
                        world
                ).ifPresentOrElse(recipeEntry -> this.outputInventory.setStack(0, recipeEntry.value().getOutput()), () -> this.outputInventory.setStack(0, ItemStack.EMPTY));
            }
        });
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
                    if (getCursorStack().isEmpty()) {
                        ItemStack output = recipeEntry.value().getOutput().copy();

                        this.inventory.removeStack(WOOL_SLOT.id, 1);
                        this.inventory.removeStack(OTHER_ITEM_SLOT.id, 1);

                        this.setCursorStack(output);
                    }
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
    public ItemStack quickMove(PlayerEntity player, int slotIndex) {
        ItemStack itemStack = ItemStack.EMPTY;

        if (slotIndex >= this.slots.size()) return itemStack;
        Slot slot = this.slots.get(slotIndex);
        if (!slot.hasStack()) return itemStack;

        ItemStack stackInSlot = slot.getStack();
        itemStack = itemStack.copy();

        if (slotIndex == WOOL_SLOT.id || slotIndex == OTHER_ITEM_SLOT.id) {
            if (!this.insertItem(stackInSlot, 3, 39, true)) {
                return ItemStack.EMPTY;
            }
        } else if (slotIndex == 2) {
            return ItemStack.EMPTY;
        } else {
            if (!this.insertItem(stackInSlot, 0, 2, false)) {
                return ItemStack.EMPTY;
            }
        }

        return itemStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
