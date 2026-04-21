package net.chemthunder.saxophone.impl.index.custom;

import net.chemthunder.saxophone.impl.util.world_event.WorldEvent;

import java.util.HashMap;
import java.util.Map;

public interface SaxoWorldEvents {
    Map<WorldEvent, String> WORLD_EVENTS = new HashMap<>();

    WorldEvent TEST = create("test");

    WorldEvent WORLD_GONE_BEAUTIFUL = create("world_gone_beautiful");
    WorldEvent NIGHT_ETERNAL = create("night_eternal");

    private static WorldEvent create(String name) {
        WorldEvent gen = new WorldEvent(name);
        WORLD_EVENTS.put(gen, name);
        return gen;
    }

    static void init() {}
}
