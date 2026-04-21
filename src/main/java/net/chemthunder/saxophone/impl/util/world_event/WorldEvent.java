package net.chemthunder.saxophone.impl.util.world_event;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record WorldEvent(String name) {
    public static final WorldEvent EMPTY = new WorldEvent("empty");

    public static final Codec<WorldEvent> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.optionalFieldOf("name", "").forGetter(WorldEvent::name)
            ).apply(instance, WorldEvent::new)
    );
}
