package net.chemthunder.oracle.impl;

import net.chemthunder.oracle.impl.index.OracleItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Oracle implements ModInitializer {
    public static final String MOD_ID = "oracle";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public void onInitialize() {
        OracleItems.init();
        LOGGER.info("oracle has been initialized from parent: saxophone");
    }

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}
