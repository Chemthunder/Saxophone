package net.chemthunder.saxophone.impl.util;

import net.minecraft.util.StringIdentifiable;

/**
 * @author Chemthunder
 */
public enum MasqueradeEventForm implements StringIdentifiable {
    BASE("base"),
    SANCTUARY("sanctuary");

    private final String name;

    MasqueradeEventForm(String s) {
        name = s;
    }

    public String asString() {
        return name;
    }
}
