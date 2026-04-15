package net.chemthunder.saxophone.impl.cca.entity;

import net.acoyt.acornlib.api.util.MiscUtils;
import net.chemthunder.saxophone.impl.Saxophone;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;

public class ForsakenCharterComponent implements AutoSyncedComponent, CommonTickingComponent {
    public static final ComponentKey<ForsakenCharterComponent> KEY = MiscUtils.getOrCreateKey(Saxophone.id("forsaken_charter"), ForsakenCharterComponent.class);
    private final LivingEntity entity;

    private int inBoxTicks = 0;
    private boolean inBox = false;

    public ForsakenCharterComponent(LivingEntity entity) {
        this.entity = entity;
    }

    public void sync() {
        KEY.sync(entity);
    }

    public void tick() {
        if (this.inBoxTicks > 0) {
            this.inBoxTicks--;
            if (!this.inBox) {
                this.inBox = true;
                this.sync();
            }

            if (this.inBoxTicks == 0) {
                this.inBox = false;
                this.sync();
            }
        }

        if (this.inBoxTicks == 0 && this.inBox) {
            this.inBox = false;
            this.sync();
        }
    }

    public boolean isInBox() {
        return this.inBox;
    }

    public void setInBox(boolean inBox) {
        this.inBox = inBox;
        this.inBoxTicks = inBox ? 3 : 0;
        this.sync();
    }

    public void readFromNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbtCompound.putInt("InBoxTicks", this.inBoxTicks);
        nbtCompound.putBoolean("InBox", this.inBox);
    }

    public void writeToNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        this.inBoxTicks = nbtCompound.getInt("InBoxTicks");
        this.inBox = nbtCompound.getBoolean("InBox");
    }
}
