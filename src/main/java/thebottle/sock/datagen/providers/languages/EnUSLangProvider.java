package thebottle.sock.datagen.providers.languages;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Util;
import thebottle.sock.enchantment.SockEnchantments;
import thebottle.sock.item.SockItems;

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
        translationBuilder.add(SockItems.VOID_SOCK, "Void Socks");
        translationBuilder.add(SockItems.H2O_SUIT, "H₂O Suit");
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
        translationBuilder.add(createTranslationKey(SockEnchantments.WATERFULL), "Waterfull");
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
        translationBuilder.add("sound.sock.h2o_suit.water_administered.water", "H₂O Suit Administers Water");
        translationBuilder.add("sound.sock.h2o_suit.water_administered.h2o", "H₂O Suit Administers H₂O");
        translationBuilder.add("sound.sock.h2o_suit.water_administered.dihydrogen_monoxide", "H₂O Suit Administers Dihydrogen Monoxide");
        translationBuilder.add("sound.sock.h2o_suit.water_administered.dihydrido_oxygen", "H₂O Suit Administers Dihydrido Oxygen");
        translationBuilder.add("sound.sock.h2o_suit.water_administered.deuterium_oxide", "H₂O Suit Administers Deuterium Oxide");
        //endregion
    }

    private String createTranslationKey(RegistryKey<Enchantment> enchantment) {
        return Util.createTranslationKey("enchantment", enchantment.getValue());
    }
}
