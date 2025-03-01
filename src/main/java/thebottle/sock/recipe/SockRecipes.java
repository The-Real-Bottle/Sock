package thebottle.sock.recipe;

import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static thebottle.sock.Util.of;

public class SockRecipes {
    public static RecipeType<SockworkingRecipe> SOCKWORKING_RECIPE_TYPE = registerRecipeType(
            SockworkingRecipe.Type.INSTANCE,
            SockworkingRecipe.Type.ID
    );
    public static RecipeSerializer<SockworkingRecipe> SOCKWORKING_RECIPE_SERIALIZER = registerRecipeSerializer(
            SockworkingRecipe.Serializer.INSTANCE,
            SockworkingRecipe.Serializer.ID
    );

    public static <T extends Recipe<?>> RecipeType<T> registerRecipeType(RecipeType<T> recipeType, String id) {
        return Registry.register(Registries.RECIPE_TYPE, of(id), recipeType);
    }

    public static <T extends Recipe<?>> RecipeSerializer<T> registerRecipeSerializer(RecipeSerializer<T> recipeSerializer, String id) {
        return Registry.register(Registries.RECIPE_SERIALIZER, of(id), recipeSerializer);
    }

    public static void init() {
    }
}
