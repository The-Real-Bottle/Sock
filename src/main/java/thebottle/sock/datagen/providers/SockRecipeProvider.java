package thebottle.sock.datagen.providers;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import thebottle.sock.item.SockItems;
import thebottle.sock.recipe.SockworkingRecipeJsonBuilder;

import java.util.concurrent.CompletableFuture;

import static thebottle.sock.Util.of;

public class SockRecipeProvider extends FabricRecipeProvider {
    public SockRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                SockworkingRecipeJsonBuilder builder = new SockworkingRecipeJsonBuilder(
                        RecipeCategory.MISC,
                        new ItemStack(SockItems.BLUE_SOCK),
                        Ingredient.ofItem(Items.BLUE_WOOL),
                        Ingredient.ofItem(Items.DIAMOND)
                );

                builder.offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, of("test_recipe")));
            }
        };
    }

    @Override
    public String getName() {
        return "Sock Recipe Generator";
    }
}
