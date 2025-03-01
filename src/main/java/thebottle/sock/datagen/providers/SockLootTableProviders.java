package thebottle.sock.datagen.providers;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;
import thebottle.sock.block.SockBlocks;

import java.util.concurrent.CompletableFuture;

public class SockLootTableProviders {
    public static class SockBlockLootTableProvider extends FabricBlockLootTableProvider {
        public SockBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
            super(dataOutput, registryLookup);
        }

        @Override
        public void generate() {
            addDrop(SockBlocks.THE_BOTTLE);
            addDrop(SockBlocks.SOCKWORKING_TABLE);
        }
    }
}
