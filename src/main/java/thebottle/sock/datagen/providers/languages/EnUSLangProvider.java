package thebottle.sock.datagen.providers.languages;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Util;
import thebottle.sock.block.SockBlocks;
import thebottle.sock.datagen.providers.SockTagProviders;
import thebottle.sock.enchantment.SockEnchantments;
import thebottle.sock.item.SockItems;
import thebottle.sock.item.groups.SockItemGroups;

import java.util.concurrent.CompletableFuture;

public class EnUSLangProvider extends FabricLanguageProvider {
    public EnUSLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        //region Items
        translationBuilder.add(SockItems.BLUE_SOCK, "Blue Socks");
        translationBuilder.add(SockItems.GREEN_SOCK, "Green Socks");
        translationBuilder.add(SockItems.WHITE_SOCK, "White Socks");
        translationBuilder.add(SockItems.TRANS_SOCK, "Trans Socks");
        translationBuilder.add(SockItems.VOID_SOCK, "Void Socks");
        translationBuilder.add(SockItems.H2O_SUIT, "H₂O Suit");

        translationBuilder.add("item.sock.sock.taunt", "How dare you take off your socks!");

        translationBuilder.add("item.sock.the_bottle.tooltip", "The Bottle™ — Bottled for Survival. Bottled for the New Order. Press sneak to place The Bottle™ itself down.");
        translationBuilder.add("item.sock.the_bottle.taunt", "%1$s, you are now dehydrated for not drinking the water from The Bottle™. Prepare for consequences");
        //endregion

        //region Blocks
        translationBuilder.add(SockBlocks.SOCKWORKING_TABLE, "Sockworking Table");
        translationBuilder.add(SockBlocks.THE_BOTTLE, "The Bottle™");
        //endregion

        //region Enchantments
        //region The Actual Enchantments
        translationBuilder.add(
                createTranslationKey(SockEnchantments.WATERPROOF),
                "Waterproof"
        );
        translationBuilder.add(
                createTranslationKey(SockEnchantments.SPEEDY),
                "Speedy"
        );
        translationBuilder.add(
                createTranslationKey(SockEnchantments.GREATER_STEPPING),
                "Greater Stepping"
        );
        translationBuilder.add(createTranslationKey(SockEnchantments.AQUA_FLUX), "Aqua Flux");
        //endregion

        //region Enchantment Level Names
        translationBuilder.add("enchantment.level.11", "XI");
        translationBuilder.add("enchantment.level.12", "XII");
        translationBuilder.add("enchantment.level.13", "XIII");
        translationBuilder.add("enchantment.level.14", "XIV");
        translationBuilder.add("enchantment.level.15", "XV");
        translationBuilder.add("enchantment.level.16", "XVI");
        translationBuilder.add("enchantment.level.17", "XVII");
        translationBuilder.add("enchantment.level.18", "XVIII");
        translationBuilder.add("enchantment.level.19", "XIX");
        translationBuilder.add("enchantment.level.20", "XX");
        //endregion
        //endregion

        //region Sounds
        translationBuilder.add("sound.sock.the_bottle.drink_the_water", "Wake Up And Drink The Water.");
        translationBuilder.add("sound.sock.h2o_suit.water_administered.water", "H₂O Suit Administers Water");
        translationBuilder.add("sound.sock.h2o_suit.water_administered.h2o", "H₂O Suit Administers H₂O");
        translationBuilder.add("sound.sock.h2o_suit.water_administered.dihydrogen_monoxide", "H₂O Suit Administers Dihydrogen Monoxide");
        translationBuilder.add("sound.sock.h2o_suit.water_administered.dihydrido_oxygen", "H₂O Suit Administers Dihydrido Oxygen");
        translationBuilder.add("sound.sock.h2o_suit.water_administered.deuterium_oxide", "H₂O Suit Administers Deuterium Oxide");
        //endregion

        //region Item Group
        translationBuilder.add(createTranslationKey(SockItemGroups.SOCKS), "Socks");
        translationBuilder.add(createTranslationKey(SockItemGroups.SOCKS_EQUIPMENT), "Equipment");
        translationBuilder.add(createTranslationKey(SockItemGroups.SOCKS_FUNCTIONAL), "Functional");
        translationBuilder.add(createTranslationKey(SockItemGroups.SOCKS_SOCKS), "Socks");
        //endregion

        //region Tags
        translationBuilder.add(SockTagProviders.SockItemTagProvider.SOCKS, "Socks");
        translationBuilder.add(SockTagProviders.SockItemTagProvider.PAPER, "Paper");
        //endregion
    }

    private String createTranslationKey(RegistryKey<Enchantment> enchantment) {
        return Util.createTranslationKey("enchantment", enchantment.getValue());
    }

    private String createTranslationKey(ItemGroup itemGroup) {
        return itemGroup.getDisplayName().getString();
    }
}
