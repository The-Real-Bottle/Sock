package thebottle.sock.datagen.providers;

import dev.emi.trinkets.TrinketsMain;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import thebottle.sock.enchantment.SockEnchantments;
import thebottle.sock.item.SockItems;

import java.util.concurrent.CompletableFuture;

import static thebottle.sock.Util.of;

public class SockTagProviders {
    public static class SockItemTagProvider extends FabricTagProvider.ItemTagProvider {
        public static final TagKey<Item> SOCKS = TagKey.of(RegistryKeys.ITEM, of("socks"));

        public SockItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
            super(output, completableFuture);
        }



        @Override
        protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
            getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, Identifier.of(TrinketsMain.MOD_ID, "feet/shoes")))
                    .add(SockItems.GREEN_SOCK)
                    .add(SockItems.BLUE_SOCK);

            getOrCreateTagBuilder(SOCKS)
                    .add(SockItems.GREEN_SOCK)
                    .add(SockItems.BLUE_SOCK);
        }
    }

    public static class SockEnchantmentTagProvider extends FabricTagProvider.EnchantmentTagProvider {
        public SockEnchantmentTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
            super(output, completableFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
            getOrCreateTagBuilder(EnchantmentTags.IN_ENCHANTING_TABLE)
                    .add(SockEnchantments.WATERPROOF)
                    .add(SockEnchantments.SPEEDY);
        }
    }
}
