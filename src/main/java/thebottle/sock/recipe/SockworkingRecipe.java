package thebottle.sock.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;

public class SockworkingRecipe implements Recipe<SockworkingRecipeInput> {
    private final Ingredient base;
    private final Ingredient other;

    private final ItemStack output;
    private final CraftingRecipeCategory category;

    public SockworkingRecipe(Ingredient base, Ingredient other, ItemStack output, CraftingRecipeCategory category) {
        this.base = base;
        this.other = other;
        this.output = output;
        this.category = category;
    }

    public CraftingRecipeCategory getCategory() {
        return category;
    }

    public Ingredient getBase() {
        return base;
    }

    public Ingredient getOther() {
        return other;
    }

    public ItemStack getOutput() {
        return output;
    }

    @Override
    public boolean matches(SockworkingRecipeInput input, World world) {
        if (input.size() < 2) return false;
        return base.test(input.getStackInSlot(0)) && other.test(input.getStackInSlot(1));
    }

    @Override
    public ItemStack craft(SockworkingRecipeInput input, RegistryWrapper.WrapperLookup registries) {
        return this.getOutput();
    }

    @Override
    public RecipeSerializer<? extends Recipe<SockworkingRecipeInput>> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<? extends Recipe<SockworkingRecipeInput>> getType() {
        return Type.INSTANCE;
    }

    @Override
    public IngredientPlacement getIngredientPlacement() {
        return IngredientPlacement.NONE;
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        return null;
    }

    public static class Serializer implements RecipeSerializer<SockworkingRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "sockworking_recipe";
        MapCodec<SockworkingRecipe> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                        Ingredient.CODEC.fieldOf("base").forGetter(SockworkingRecipe::getBase),
                        Ingredient.CODEC.fieldOf("other").forGetter(SockworkingRecipe::getOther),
                        ItemStack.CODEC.fieldOf("output").forGetter(SockworkingRecipe::getOutput),
                        CraftingRecipeCategory.CODEC.fieldOf("category").orElse(CraftingRecipeCategory.MISC).forGetter(SockworkingRecipe::getCategory)
                ).apply(instance, SockworkingRecipe::new)
        );

        private Serializer() {
        }

        @Override
        public MapCodec<SockworkingRecipe> codec() {
            return CODEC;
        }

        @Deprecated
        @Override
        public PacketCodec<RegistryByteBuf, SockworkingRecipe> packetCodec() {
            return PacketCodec.ofStatic(
                    (buf, recipe) -> {
                        Ingredient.PACKET_CODEC.encode(buf, recipe.base);
                        Ingredient.PACKET_CODEC.encode(buf, recipe.other);

                        ItemStack.PACKET_CODEC.encode(buf, recipe.output);

                        buf.writeEnumConstant(recipe.category);
                    },
                    buf -> {
                        Ingredient base = Ingredient.PACKET_CODEC.decode(buf);
                        Ingredient other = Ingredient.PACKET_CODEC.decode(buf);

                        ItemStack output = ItemStack.PACKET_CODEC.decode(buf);

                        CraftingRecipeCategory category = buf.readEnumConstant(CraftingRecipeCategory.class);

                        return new SockworkingRecipe(base, other, output, category);
                    }
            );
        }
    }

    public static class Type implements RecipeType<SockworkingRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "sockworking_recipe";

        private Type() {
        }
    }
}
