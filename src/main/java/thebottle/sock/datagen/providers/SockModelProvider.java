package thebottle.sock.datagen.providers;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;
import thebottle.sock.block.SockBlocks;
import thebottle.sock.item.SockItems;

public class SockModelProvider extends FabricModelProvider {
    public SockModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleState(SockBlocks.SOCKWORKING_TABLE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(SockItems.BLUE_SOCK, Models.GENERATED);
        itemModelGenerator.register(SockItems.GREEN_SOCK, Models.GENERATED);
        itemModelGenerator.register(SockItems.VOID_SOCK, Models.GENERATED);

        itemModelGenerator.register(SockItems.H2O_SUIT);
    }
}
