package thebottle.sock.datagen.providers.languages;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import thebottle.sock.item.SockItems;

import java.util.concurrent.CompletableFuture;

public class EnUSLangProvider extends FabricLanguageProvider {
    public EnUSLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(SockItems.BLUE_SOCK, "Blue Socks");
        
        translationBuilder.add("subtitles.sock.drink_the_water", "Wake Up And Drink The Water. Press sneak to place The Bottle™ itself down.");
        translationBuilder.add(SockItems.THE_BOTTLE_ITEM, "The Bottle™");
        translationBuilder.add("item.sock.the_bottle.tooltip", "The Bottle™ — Bottled for Survival. Bottled for the New Order.");
        translationBuilder.add("item.sock.the_bottle.taunt", "%1$s, you are now dehydrated for not drinking the water from The Bottle™. Prepare for consequences");
    }
}
