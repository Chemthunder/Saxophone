package net.chemthunder.reverence.impl;

import net.chemthunder.reverence.impl.index.ReverenceItemGroups;
import net.chemthunder.reverence.impl.index.ReverenceItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Reverence implements ModInitializer {
    public static final String MOD_ID = "reverence";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public void onInitialize() {
        ReverenceItems.init();
        ReverenceItemGroups.init();

        LOGGER.info("reverenced has been initialized from parent: saxophone");
    }

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}
