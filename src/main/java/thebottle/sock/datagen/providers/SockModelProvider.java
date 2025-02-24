package thebottle.sock.datagen.providers;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.*;
import net.minecraft.util.math.Direction;
import thebottle.sock.Util;
import thebottle.sock.block.SockBlocks;
import thebottle.sock.block.TheBottle;
import thebottle.sock.item.SockItems;

import java.util.Optional;

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
                        .coordinate(BlockStateVariantMap.create(TheBottle.FACING)
                                .register(Direction.NORTH, BlockStateVariant.create())
                                .register(Direction.SOUTH, BlockStateVariant.create())
                                .register(Direction.EAST, BlockStateVariant.create())
                                .register(Direction.WEST, BlockStateVariant.create())
                        ));
    }

    private static Model block(String parent, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(Util.ofBlock(parent)), Optional.empty(), requiredTextureKeys);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(SockItems.BLUE_SOCK, Models.GENERATED);
    }
}
