package thebottle.sock.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import thebottle.sock.item.SockItems;

import java.util.List;
import java.util.stream.Stream;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {
    @Inject(method = "generateEnchantments", at = @At("HEAD"), cancellable = true)
    private static void generateEnchantmentsInjection(Random random, ItemStack stack, int level, Stream<RegistryEntry<Enchantment>> possibleEnchantments, CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir) {
        if (stack.isOf(SockItems.SOCK)) {
            cir.setReturnValue(
                    possibleEnchantments
                            .filter(enchantment -> enchantment.value().isSupportedItem(stack))
                            .map(enchantment -> new EnchantmentLevelEntry(enchantment, enchantment.value().getMaxLevel()))
                            .toList()
            );
        }
    }
}
