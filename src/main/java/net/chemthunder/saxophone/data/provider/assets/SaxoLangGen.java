package net.chemthunder.saxophone.data.provider.assets;

import net.chemthunder.saxophone.core.index.SaxoItems;
import net.chemthunder.saxophone.core.index.SaxoStatusEffects;
import net.chemthunder.saxophone.core.index.data.SaxoDamageSources;
import net.chemthunder.saxophone.core.index.tag.SaxoDamageTypeTags;
import net.chemthunder.saxophone.core.util.DatagenUtils;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

/**
 * @author Chemthunder
 */
public class SaxoLangGen extends FabricLanguageProvider {
    public SaxoLangGen(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        SaxoItems.ITEMS.registerLang(wrapperLookup, translationBuilder);
        SaxoDamageTypeTags.registerLang(translationBuilder);
        SaxoStatusEffects.STATUS_EFFECTS.registerLang(wrapperLookup, translationBuilder);

        translationBuilder.add("itemGroup.saxophone", "Saxophone");

        // Item Misc
        translationBuilder.add("item.saxophone.contract_signed", "Signed Contract");

        // Subtitles
        translationBuilder.add("sounds.saxophone.liberation_swing", "Liberation Swings");
        translationBuilder.add("sounds.saxophone.bell_toll", "Virtus Dei tolls");
        translationBuilder.add("sounds.saxophone.covetous_monolith", "Covetous Monolith is placed");

        // Damage Sources
        DatagenUtils.Lang.registerSingleMessageDamageType(translationBuilder, SaxoDamageSources.AVARICES_WILL,
                "%1$s was claimed by unknown forces"
        );

        DatagenUtils.Lang.registerSingleMessageDamageType(translationBuilder, SaxoDamageSources.CLEANSE,
                "%1$s had their soul cleansed of sin"
        );

        DatagenUtils.Lang.registerDamageType(translationBuilder, SaxoDamageSources.LIBERATE,
                "%1$s was liberated",
                "%1$s was liberated by %2$s wielding %3$s",
                "%1$s was liberated by %2$s"
        );

        // Keybindings
        translationBuilder.add("category.saxophone", "Saxophone");
        translationBuilder.add("key.saxophone.explode_ivory", "Cause Scarlet to detonate");
    }
}