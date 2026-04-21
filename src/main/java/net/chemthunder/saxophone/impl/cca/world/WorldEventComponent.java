package net.chemthunder.saxophone.impl.cca.world;

import net.acoyt.acornlib.api.util.MiscUtils;
import net.chemthunder.saxophone.impl.Saxophone;
import net.chemthunder.saxophone.impl.util.ModUtils;
import net.chemthunder.saxophone.impl.util.world_event.WorldEvent;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtOps;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;

public class WorldEventComponent implements AutoSyncedComponent, CommonTickingComponent {
    public static final ComponentKey<WorldEventComponent> KEY = MiscUtils.getOrCreateKey(Saxophone.id("world_event"), WorldEventComponent.class);

    private final World world;

    private int duration = 0;

    //MAIN
    private WorldEvent activeEvent = WorldEvent.EMPTY;

    public WorldEventComponent(World world) {
        this.world = world;
    }

    public void sync() {
        KEY.sync(world);
    }

    public void tick() {
        if (this.duration > 0) {
            this.duration--;
            if (this.duration == 0) {
                this.sync();
            }
        }
    }

    public void readFromNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        this.duration = nbtCompound.getInt("Duration");

        if (nbtCompound.contains("ActiveWorldEvent", NbtElement.COMPOUND_TYPE)) {
            NbtCompound compound = nbtCompound.getCompound("ActiveWorldEvent");
            this.activeEvent = WorldEvent.CODEC.parse(wrapperLookup.getOps(NbtOps.INSTANCE), compound).resultOrPartial(Saxophone.LOGGER::error).orElseThrow();
        } else {
            this.activeEvent = WorldEvent.EMPTY;
        }
    }

    public void writeToNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbtCompound.putInt("Duration", duration);

        if (this.activeEvent != WorldEvent.EMPTY) {
            nbtCompound.put("ActiveWorldEvent", WorldEvent.CODEC.encodeStart(wrapperLookup.getOps(NbtOps.INSTANCE), this.activeEvent).getOrThrow());
        }
    }

    public int getDuration() {
        return this.duration;
    }

    public WorldEvent getActiveEvent() {
        return this.activeEvent;
    }

    public void setDuration(int i) {
        this.duration = i;
        this.sync();
    }

    public void setActiveEvent(WorldEvent worldEvent) {
        this.activeEvent = worldEvent;
        this.beginWorldEvent(worldEvent);
        this.sync();
    }

    public void setActiveEvent(WorldEvent worldEvent, int duration) {
        this.activeEvent = worldEvent;
        this.duration = duration;
        this.beginWorldEvent(worldEvent);
        this.sync();
    }

    public void removeActiveEvent() {
        this.activeEvent = WorldEvent.EMPTY;
        this.duration = 0;
        this.endWorldEvent();
        this.sync();
    }

    public void beginWorldEvent(WorldEvent event) {
        this.world.getPlayers().forEach(player -> {
            player.sendMessage(Text.translatable(ModUtils.getEventId(event)).append(Text.literal(" has begun.").withColor(0xd70048).formatted(Formatting.ITALIC)), true);
            player.playSoundToPlayer(SoundEvents.ENTITY_ALLAY_HURT, SoundCategory.PLAYERS, 1, 1);
        });
    }

    public void endWorldEvent() {
        this.world.getPlayers().forEach(player -> {
            player.sendMessage(Text.literal("Ended all world events"), true);
            player.playSoundToPlayer(SoundEvents.ENTITY_ALLAY_HURT, SoundCategory.PLAYERS, 1, 1);
        });
    }
}
