package thebottle.sock.datagen.providers;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import thebottle.sock.block.SockBlocks;
import thebottle.sock.item.SockItems;
import thebottle.sock.recipe.SockworkingRecipeJsonBuilder;

import java.util.concurrent.CompletableFuture;

public class SockRecipeProvider extends FabricRecipeProvider {
    public SockRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            private final RegistryEntryLookup<Item> itemLookup = wrapperLookup.getOrThrow(RegistryKeys.ITEM);

            @Override
            public void generate() {
                SockworkingRecipeJsonBuilder.create(itemLookup, RecipeCategory.MISC, SockItems.BLUE_SOCK)
                        .setWool(Items.BLUE_WOOL)
                        .setOther(Items.DIAMOND)
                        .criterion(hasItem(Items.BLUE_WOOL), conditionsFromItem(Items.BLUE_WOOL))
                        .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                        .offerTo(exporter, getItemPath(SockItems.BLUE_SOCK));

                SockworkingRecipeJsonBuilder.create(itemLookup, RecipeCategory.MISC, SockItems.GREEN_SOCK)
                        .setWool(Items.GREEN_WOOL)
                        .setOther(Items.EMERALD_BLOCK)
                        .criterion(hasItem(Items.GREEN_WOOL), conditionsFromItem(Items.GREEN_WOOL))
                        .criterion(hasItem(Items.EMERALD_BLOCK), conditionsFromItem(Items.EMERALD_BLOCK))
                        .offerTo(exporter, getItemPath(SockItems.GREEN_SOCK));

                SockworkingRecipeJsonBuilder.create(itemLookup, RecipeCategory.MISC, SockItems.WHITE_SOCK)
                        .setWool(Items.WHITE_WOOL)
                        .setOther(Items.IRON_INGOT)
                        .criterion(hasItem(Items.WHITE_WOOL), conditionsFromItem(Items.WHITE_WOOL))
                        .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                        .offerTo(exporter, getItemPath(SockItems.WHITE_SOCK));

                SockworkingRecipeJsonBuilder.create(itemLookup, RecipeCategory.MISC, SockItems.TRANS_SOCK)
                        .setWool(Items.WHITE_WOOL)
                        .setOther(Items.SHULKER_SHELL)
                        .criterion(hasItem(Items.WHITE_WOOL), conditionsFromItem(Items.WHITE_WOOL))
                        .criterion(hasItem(Items.SHULKER_SHELL), conditionsFromItem(Items.SHULKER_SHELL))
                        .offerTo(exporter, getItemPath(SockItems.TRANS_SOCK));

                SockworkingRecipeJsonBuilder.create(itemLookup, RecipeCategory.MISC, SockItems.VOID_SOCK)
                        .setWool(SockTagProviders.SockItemTagProvider.SOCKS)
                        .setOther(Items.NETHER_STAR)
                        .criterion(hasItem(Items.NETHER_STAR), conditionsFromItem(Items.NETHER_STAR))
                        .offerTo(exporter, getItemPath(SockItems.VOID_SOCK));

                ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.DECORATIONS, SockBlocks.SOCKWORKING_TABLE)
                        .pattern("PPP")
                        .pattern(" S ")
                        .pattern("S S")
                        .input('S', Items.STICK)
                        .input('P', Blocks.PALE_OAK_PLANKS)
                        .criterion(hasItem(Blocks.PALE_OAK_LOG), conditionsFromItem(Blocks.PALE_OAK_LOG))
                        .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                        .offerTo(exporter, getItemPath(SockBlocks.SOCKWORKING_TABLE));

                ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.TOOLS, SockBlocks.THE_BOTTLE)
                        .pattern("PBP")
                        .pattern("GBG")
                        .pattern("GGG")
                        .input('G', Items.GLASS_PANE)
                        .input('B', Items.WATER_BUCKET)
                        .input('P', Blocks.PALE_OAK_PLANKS)
                        .criterion(hasItem(Items.WATER_BUCKET), conditionsFromItem(Items.WATER_BUCKET))
                        .offerTo(exporter, getItemPath(SockBlocks.THE_BOTTLE));

                ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.COMBAT, SockItems.H2O_SUIT)
                        .pattern("P P")
                        .pattern("PPP")
                        .pattern("PPP")
                        .input('P', SockTagProviders.SockItemTagProvider.PAPER)
                        .criterion(hasItem(Items.PAPER), conditionsFromItem(Items.PAPER))
                        .offerTo(exporter, getItemPath(SockItems.H2O_SUIT));
            }
        };
    }

    @Override
    public String getName() {
        return "Sock Recipe Generator";
    }
}
