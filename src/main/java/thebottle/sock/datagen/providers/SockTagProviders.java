package thebottle.sock.datagen.providers;

import dev.emi.trinkets.TrinketsMain;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import thebottle.sock.block.SockBlocks;
import thebottle.sock.enchantment.SockEnchantments;
import thebottle.sock.item.SockItems;

import java.util.concurrent.CompletableFuture;

import static thebottle.sock.Util.of;

public class SockTagProviders {
    public static class SockItemTagProvider extends FabricTagProvider.ItemTagProvider {
        public static final TagKey<Item> SOCKS = TagKey.of(RegistryKeys.ITEM, of("socks"));
        public static final TagKey<Item> PAPER = TagKey.of(RegistryKeys.ITEM, Identifier.of("c", "paper"));

        public SockItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
            super(output, completableFuture);
        }


        @Override
        protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
            getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, Identifier.of(TrinketsMain.MOD_ID, "feet/shoes")))
                    .add(SockItems.GREEN_SOCK)
                    .add(SockItems.VOID_SOCK)
                    .add(SockItems.BLUE_SOCK)
                    .setReplace(false);

            getOrCreateTagBuilder(SOCKS)
                    .add(SockItems.GREEN_SOCK)
                    .add(SockItems.VOID_SOCK)
                    .add(SockItems.BLUE_SOCK);

            getOrCreateTagBuilder(PAPER)
                    .add(Items.PAPER);
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

    public static class SockEnchantmentTagProvider extends FabricTagProvider.EnchantmentTagProvider {
        public SockEnchantmentTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
            super(output, completableFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
            getOrCreateTagBuilder(EnchantmentTags.IN_ENCHANTING_TABLE)
                    .add(SockEnchantments.WATERPROOF)
                    .add(SockEnchantments.WATERFULL)
                    .add(SockEnchantments.SPEEDY);
        }
    }
}
