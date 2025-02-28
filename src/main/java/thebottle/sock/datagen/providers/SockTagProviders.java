package thebottle.sock.datagen.providers;

import dev.emi.trinkets.TrinketsMain;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import thebottle.sock.block.SockBlocks;
import thebottle.sock.item.SockItems;

import java.util.concurrent.CompletableFuture;

public class SockTagProviders {
    public static class SockItemTagProvider extends FabricTagProvider.ItemTagProvider {
        public SockItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
            super(output, completableFuture);
        }


        @Override
        protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
            getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, Identifier.of(TrinketsMain.MOD_ID, "feet/shoes")))
                    .add(SockItems.BLUE_SOCK)
                    .setReplace(false);

        }
    }

    public static class SockBlockTagProvider extends FabricTagProvider.BlockTagProvider {
        public SockBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
            super(output, completableFuture);
        }


        @Override
        protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
            getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, Identifier.ofVanilla("mineable/pickaxe")))
                    .add(SockBlocks.THE_BOTTLE)
                    .setReplace(false);

        }
    }
}
