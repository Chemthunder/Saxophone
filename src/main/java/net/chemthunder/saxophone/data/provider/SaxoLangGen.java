package net.chemthunder.saxophone.data.provider;

import net.chemthunder.saxophone.impl.index.SaxoItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class SaxoLangGen extends FabricLanguageProvider {
    public SaxoLangGen(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        SaxoItems.ITEMS.registerLang(wrapperLookup, translationBuilder);

        translationBuilder.add("itemGroup.saxophone", "Saxophone");

        translationBuilder.add("item.saxophone.contract_signed", "Signed Contract");
    }
}
