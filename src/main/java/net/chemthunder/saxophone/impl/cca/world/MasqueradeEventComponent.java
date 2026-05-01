package net.chemthunder.saxophone.impl.cca.world;

import net.acoyt.acornlib.api.util.MiscUtils;
import net.chemthunder.saxophone.impl.Saxophone;
import net.chemthunder.saxophone.impl.util.MasqueradeEventForm;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

/**
 * @author Chemthunder
 */
public class MasqueradeEventComponent implements AutoSyncedComponent {
    public static final ComponentKey<MasqueradeEventComponent> KEY = MiscUtils.getOrCreateKey(Saxophone.id("masquerade_event"), MasqueradeEventComponent.class);
    private final World world;

    private boolean state = false;
    private boolean sanctuary = false;
    
    public MasqueradeEventComponent(World world) {
        this.world = world;
    }

    public void readFromNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        this.state = nbtCompound.getBoolean("State");
        this.sanctuary = nbtCompound.getBoolean("Sanctuary");
    }

    public void writeToNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbtCompound.putBoolean("State", state);
        nbtCompound.putBoolean("Sanctuary", sanctuary);
    }

    public void setState(boolean bl) {
        this.state = bl;
    }

    public boolean getState() {
        return this.state;
    }

    public void setSanctuary(boolean bl) {
        this.sanctuary = bl;
    }

    public boolean getSanctuary() {
        return this.sanctuary;
    }
}
