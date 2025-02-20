package thebottle.sock.datagen.providers;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import thebottle.sock.item.SockItems;

import java.util.concurrent.CompletableFuture;

public class SockTagProviders {
    public static class SockItemTagProvider extends FabricTagProvider.ItemTagProvider {
        public SockItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
            super(output, completableFuture);
        }



        @Override
        protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
            getOrCreateTagBuilder(ItemTags.FOOT_ARMOR)
                    .add(SockItems.SOCK);
        }
    }
}
