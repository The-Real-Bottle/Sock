package thebottle.sock.recipe;

import net.minecraft.advancement.*;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;

public class SockworkingRecipeJsonBuilder implements CraftingRecipeJsonBuilder {
    private final RecipeCategory category;
    private final ItemStack output;
    private final Ingredient base;
    private final Ingredient other;
    private final Map<String, AdvancementCriterion<?>> advancementBuilder = new LinkedHashMap<>();
    @Nullable
    private String group;

    private SockworkingRecipeJsonBuilder(RecipeCategory category, ItemStack output, Ingredient base, Ingredient other) {
        this.category = category;
        this.output = output;
        this.base = base;
        this.other = other;
    }

    public static SockworkingRecipeJsonBuilder create(RecipeCategory category, ItemStack output, Ingredient base, Ingredient other) {
        return new SockworkingRecipeJsonBuilder(category, output, base, other);
    }

    static Identifier getItemId(ItemConvertible item) {
        return Registries.ITEM.getId(item.asItem());
    }

    @Override
    public CraftingRecipeJsonBuilder criterion(String name, AdvancementCriterion<?> criterion) {
        this.advancementBuilder.put(name, criterion);
        return this;
    }

    @Override
    public CraftingRecipeJsonBuilder group(@Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public Item getOutputItem() {
        return output.getItem();
    }

    @Override
    public void offerTo(RecipeExporter exporter, RegistryKey<Recipe<?>> recipeKey) {
        Advancement.Builder builder = exporter.getAdvancementBuilder()
                .criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeKey))
                .rewards(AdvancementRewards.Builder.recipe(recipeKey))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.OR);

        SockworkingRecipe recipe = new SockworkingRecipe(
                this.base,
                this.other,
                this.output,
                CraftingRecipeJsonBuilder.toCraftingCategory(this.category)
        );

        AdvancementEntry advancement = builder.build(recipeKey.getValue().withPrefixedPath("recipes/" + this.category.getName() + "/"));

        exporter.accept(recipeKey, recipe, advancement);
    }

    public void offerTo(RecipeExporter exporter) {
        offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, getItemId(this.getOutputItem())));
    }
}
