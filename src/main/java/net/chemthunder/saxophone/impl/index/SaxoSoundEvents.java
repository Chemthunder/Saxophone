package net.chemthunder.saxophone.impl.index;

import net.acoyt.acornlib.api.registrants.SoundEventRegistrant;
import net.chemthunder.saxophone.impl.Saxophone;
import net.minecraft.sound.SoundEvent;

public interface SaxoSoundEvents {
    SoundEventRegistrant SOUND_EVENTS = new SoundEventRegistrant(Saxophone.MOD_ID);

    SoundEvent LIBERATION_SWING = SOUND_EVENTS.register("liberation_swing");
    SoundEvent BELL_TOLL = SOUND_EVENTS.register("bell_toll");

    static void init() {}
}