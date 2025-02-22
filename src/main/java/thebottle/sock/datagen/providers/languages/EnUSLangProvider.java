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
    }
}
