package thebottle.sock.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;
import net.minecraft.util.collection.DefaultedList;

public class SockworkingRecipeInput implements RecipeInput {
    public static final SockworkingRecipeInput EMPTY = new SockworkingRecipeInput(DefaultedList.of());
    private final DefaultedList<ItemStack> items;

    private SockworkingRecipeInput(DefaultedList<ItemStack> items) {
        this.items = items;
    }

    public static SockworkingRecipeInput of(DefaultedList<ItemStack> items) {
        return new SockworkingRecipeInput(items);
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return items.get(slot);
    }

    @Override
    public int size() {
        return items.size();
    }
}
