package thebottle.sock.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import thebottle.sock.block.screen.SockworkingTableScreenHandler;

public class SockworkingTableBlockEntity extends BlockEntity implements NamedScreenHandlerFactory {
    public SockworkingTableBlockEntity(BlockPos pos, BlockState state) {
        super(SockBlocks.SOCKWORKING_TABLE_BLOCK_ENTITY, pos, state);
    }

    @Override
    public Text getDisplayName() {
        return Text.of("Sockworking Table");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new SockworkingTableScreenHandler(syncId, playerInventory);
    }
}
