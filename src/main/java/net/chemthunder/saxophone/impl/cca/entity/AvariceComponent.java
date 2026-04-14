package net.chemthunder.saxophone.impl.cca.entity;

import net.acoyt.acornlib.api.util.MiscUtils;
import net.chemthunder.saxophone.impl.Saxophone;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

public class AvariceComponent implements AutoSyncedComponent {
    public static final ComponentKey<AvariceComponent> KEY = MiscUtils.getOrCreateKey(Saxophone.id("avarice"), AvariceComponent.class);
    private final PlayerEntity player;
    
    private boolean avarice = false;
    private boolean invisible = false;

    public AvariceComponent(PlayerEntity player) {
        this.player = player;
    }

    public void sync() {
        KEY.sync(player);
    }

    public void readFromNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        this.avarice = nbtCompound.getBoolean("Avarice");
        this.invisible = nbtCompound.getBoolean("Invisible");
    }

    public void writeToNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbtCompound.putBoolean("Avarice", avarice);
        nbtCompound.putBoolean("Invisible", invisible);
    }

    public boolean isAvarice() {
        return this.avarice;
    }

    public void setAvarice(boolean bl) {
        this.avarice = bl;
        sync();
    }

    public boolean isInvisible() {
        return this.invisible;
    }

    public void setInvisible(boolean bl) {
        this.invisible = bl;
        sync();
    }
}