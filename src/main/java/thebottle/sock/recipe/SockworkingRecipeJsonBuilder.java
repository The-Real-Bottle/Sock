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
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class SockworkingRecipeJsonBuilder implements CraftingRecipeJsonBuilder {
    private final RecipeCategory category;
    private final ItemStack output;
    private Ingredient base;
    private Ingredient other;
    private final RegistryEntryLookup<Item> itemLookup;
    private final Map<String, AdvancementCriterion<?>> advancementBuilder = new LinkedHashMap<>();
    @Nullable
    private String group;

    private SockworkingRecipeJsonBuilder(RegistryEntryLookup<Item> itemLookup, RecipeCategory category, ItemStack output) {
        this.itemLookup = itemLookup;
        this.category = category;
        this.output = output;
    }

    public static SockworkingRecipeJsonBuilder create(RegistryEntryLookup<Item> registryLookup, RecipeCategory category, ItemConvertible output) {
        return new SockworkingRecipeJsonBuilder(registryLookup, category, output.asItem().getDefaultStack());
    }

    public static SockworkingRecipeJsonBuilder create(RegistryEntryLookup<Item> registryEntryLookup, RecipeCategory category, ItemConvertible output, int count) {
        return new SockworkingRecipeJsonBuilder(registryEntryLookup, category, new ItemStack(output, count));
    }

    public SockworkingRecipeJsonBuilder setWool(ItemConvertible item) {
        this.base = Ingredient.ofItem(item);
        return this;
    }

    public SockworkingRecipeJsonBuilder setWool(TagKey<Item> tag) {
        this.base = Ingredient.fromTag(itemLookup.getOrThrow(tag));
        return this;
    }

    public SockworkingRecipeJsonBuilder setOther(ItemConvertible item) {
        this.other = Ingredient.ofItem(item);
        return this;
    }

    public SockworkingRecipeJsonBuilder setOther(TagKey<Item> tag) {
        this.other = Ingredient.fromTag(itemLookup.getOrThrow(tag));
        return this;
    }

    static Identifier getItemId(ItemConvertible item) {
        return Registries.ITEM.getId(item.asItem());
    }

    @Override
    public SockworkingRecipeJsonBuilder criterion(String name, AdvancementCriterion<?> criterion) {
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
        validate();
        Advancement.Builder builder = exporter.getAdvancementBuilder()
                .criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeKey))
                .rewards(AdvancementRewards.Builder.recipe(recipeKey))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.OR);

        this.advancementBuilder.forEach(builder::criterion);

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

    private void validate() {
        Objects.requireNonNull(base, "Sockworking recipe base must not be empty in recipe " + this);
        Objects.requireNonNull(other, "Sockworking recipe other must not be empty in recipe" + this);
    }

    @Override
    public String toString() {
        return "SockworkingRecipeJsonBuilder{" +
                "category=" + category +
                ", output=" + output +
                ", base=" + base +
                ", other=" + other +
                ", itemLookup=" + itemLookup +
                ", advancementBuilder=" + advancementBuilder +
                ", group='" + group + '\'' +
                '}';
    }
}
