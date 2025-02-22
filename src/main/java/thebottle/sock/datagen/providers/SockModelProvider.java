package thebottle.sock.datagen.providers;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.FacingBlock;
import net.minecraft.client.data.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;
import thebottle.sock.Util;
import thebottle.sock.block.SockBlocks;
import thebottle.sock.item.SockItems;

public class SockModelProvider extends FabricModelProvider {
    public SockModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.blockStateCollector.accept(
                VariantsBlockStateSupplier.create(
                        SockBlocks.THE_BOTTLE,
                        BlockStateVariant.create().put(VariantSettings.MODEL, Util.ofBlock(SockBlocks.THE_BOTTLE_ID)))
                            .coordinate(BlockStateVariantMap.create(Properties.FACING).register(Direction.NORTH, BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R0))
                                    .register(Direction.SOUTH, BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R180))
                                    .register(Direction.EAST, BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R180))
                                    .register(Direction.WEST, BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R180))
                                    .register(Direction.UP, BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R0))
                                    .register(Direction.DOWN, BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R0))
                            ));
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(SockItems.BLUE_SOCK, Models.GENERATED);
        itemModelGenerator.register(SockItems.THE_BOTTLE_ITEM, Models.GENERATED);
    }
}
