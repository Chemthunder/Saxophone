package net.chemthunder.oracle.impl;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Oracle implements ModInitializer {
    public static final String MOD_ID = "reverence";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public void onInitialize() {
        LOGGER.info("oracle has been initialized from parent: saxophone");
    }

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}
