package thebottle.sock.datagen.providers;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
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
                SockworkingRecipeJsonBuilder.create(
                        RecipeCategory.MISC,
                        new ItemStack(SockItems.BLUE_SOCK),
                        Ingredient.ofItem(Items.BLUE_WOOL),
                        Ingredient.ofItem(Items.DIAMOND)
                ).offerTo(exporter);

                SockworkingRecipeJsonBuilder.create(
                        RecipeCategory.MISC,
                        new ItemStack(SockItems.GREEN_SOCK),
                        Ingredient.ofItem(Items.GREEN_WOOL),
                        Ingredient.ofItem(Blocks.EMERALD_BLOCK)
                ).offerTo(exporter);

                SockworkingRecipeJsonBuilder.create(
                        RecipeCategory.MISC,
                        new ItemStack(SockItems.VOID_SOCK),
                        Ingredient.fromTag(itemLookup.getOrThrow(SockTagProviders.SockItemTagProvider.SOCKS)),
                        Ingredient.ofItem(Items.NETHER_STAR)
                ).offerTo(exporter);

                ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.DECORATIONS, SockBlocks.SOCKWORKING_TABLE)
                        .pattern("PPP")
                        .pattern(" S ")
                        .pattern("S S")
                        .input('S', Items.STICK)
                        .input('P', Blocks.PALE_OAK_PLANKS)
                        .criterion(hasItem(Blocks.PALE_OAK_LOG), conditionsFromItem(Blocks.PALE_OAK_LOG))
                        .offerTo(exporter, getItemPath(SockBlocks.SOCKWORKING_TABLE));

                ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.DECORATIONS, SockBlocks.THE_BOTTLE)
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
