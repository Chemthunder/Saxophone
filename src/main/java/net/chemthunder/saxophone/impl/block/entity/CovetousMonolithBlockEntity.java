package net.chemthunder.saxophone.impl.block.entity;

import com.nitron.nitrogen.util.interfaces.ScreenShaker;
import net.chemthunder.saxophone.impl.index.SaxoBlockEntities;
import net.fabricmc.fabric.impl.renderer.VanillaModelEncoder;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.data.client.ModelProvider;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class CovetousMonolithBlockEntity extends BlockEntity {
    public int ageTicks = 0;
    public int radius = 0;

    public CovetousMonolithBlockEntity(BlockPos pos, BlockState state) {
        super(SaxoBlockEntities.COVETOUS_MONOLITH, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, @NotNull CovetousMonolithBlockEntity monolith) {
        monolith.ageTicks++;

        if (monolith.ageTicks < 75) {
            monolith.radius++;
        }

        if (monolith.ageTicks == 75) {
            world.getPlayers().forEach(player -> {
                if (player instanceof ScreenShaker shaker) {
                    shaker.addScreenShake(3.0f, 20);
                }
            });
        }
    }

    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        this.ageTicks = nbt.getInt("AgeTicks");
        this.radius = nbt.getInt("Radius");
        super.readNbt(nbt, registryLookup);
    }

    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        nbt.putInt("AgeTicks", ageTicks);
        nbt.putInt("Radius", radius);
        super.writeNbt(nbt, registryLookup);
    }
}
