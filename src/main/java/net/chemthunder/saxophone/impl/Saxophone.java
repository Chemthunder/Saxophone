package net.chemthunder.saxophone.impl;

import com.everest.hibiscus.api.modules.rendering.text.HibiscusPresetEffects;
import com.everest.hibiscus.api.modules.rendering.text.registry.TextEffectManager;
import net.acoyt.acornlib.api.ALib;
import net.chemthunder.saxophone.impl.command.AvariceCommands;
import net.chemthunder.saxophone.impl.index.SaxoDataComponents;
import net.chemthunder.saxophone.impl.index.SaxoItemGroups;
import net.chemthunder.saxophone.impl.index.SaxoItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.entity.Entity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class Saxophone implements ModInitializer {
	public static final String MOD_ID = "saxophone";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final int modColor = 0xd70048;
    private static final MutableText MOD_NAME = Text.literal("Saxophone");
    private static final MutableText MOD_SUMMARY = Text.literal("Consequences of your actions.");

    private static final ALib.ModMenuData DATA = new ALib.ModMenuData(
            MOD_NAME.withColor(0xd70048),
            MOD_SUMMARY.withColor(0xd70048),
            Text.literal("\"I looked down upon a feeble world, and breathed in excitement. The world is yours to take, and I reached for the stars.\"").formatted(Formatting.ITALIC)
    );

	public void onInitialize() {
        SaxoItems.init();
        SaxoDataComponents.init();
        SaxoItemGroups.init();

        CommandRegistrationCallback.EVENT.register(new AvariceCommands());

		LOGGER.info("Hello Fabric world!");

        ALib.registerModData(MOD_ID, DATA);
	}

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }

    public static boolean isScarlet(Entity entity) {
        return entity != null && entity.getUuid().equals(UUID.fromString("c38f83cf-2723-497a-9327-f5937fb2fc08"));
    }
}