package net.chemthunder.saxophone.core.cca.deity;

import net.acoyt.acornlib.api.util.MiscUtils;
import net.chemthunder.saxophone.core.Saxophone;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;

public class EosComponent implements AutoSyncedComponent, CommonTickingComponent {
    public static final ComponentKey<EosComponent> KEY = MiscUtils.getOrCreateKey(Saxophone.id("eos"), EosComponent.class);
    private final PlayerEntity player;

    private boolean eos = false;
    private boolean flight = false;

    public EosComponent(PlayerEntity player) {
        this.player = player;
    }

    public void sync() {
        KEY.sync(player);
    }

    public void tick() {
        if (this.isEos()) {
            if (this.canFly()) {
                this.player.getAbilities().allowFlying = true;
            }
        }
    }

    public void readFromNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        this.eos = nbtCompound.getBoolean("Eos");
        this.flight = nbtCompound.getBoolean("Flight");
    }

    public void writeToNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbtCompound.putBoolean("Eos", eos);
        nbtCompound.putBoolean("Flight", flight);
    }

    public boolean isEos() {
        return this.eos;
    }

    public boolean canFly() {
        return this.flight;
    }

    public void setEos(boolean bl) {
        this.eos = bl;
        this.sync();
    }

    public void setFlight(boolean bl) {
        this.flight = bl;
        this.sync();
    }

    public void set(boolean eos, boolean flight) {
        this.eos = eos;
        this.flight = flight;
        this.sync();
    }
}
