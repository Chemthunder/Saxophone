package net.chemthunder.saxophone.impl.block.entity;

import com.nitron.nitrogen.util.interfaces.ScreenShaker;
import net.chemthunder.saxophone.impl.index.SaxoBlockEntities;
import net.chemthunder.saxophone.impl.index.SaxoItems;
import net.chemthunder.saxophone.impl.util.ModUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CovetousMonolithBlockEntity extends BlockEntity {
    public int ageTicks = 0;
    public int radius = 0;

    private final int getMaxRadius = 75;
    private final int secondsToFinalize = 10;

    public CovetousMonolithBlockEntity(BlockPos pos, BlockState state) {
        super(SaxoBlockEntities.COVETOUS_MONOLITH, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, @NotNull CovetousMonolithBlockEntity monolith) {
        monolith.ageTicks++;

        if (monolith.ageTicks < monolith.getMaxRadius) {
            monolith.radius++;
        }

        if (monolith.ageTicks == monolith.getMaxRadius) {
            world.getPlayers().forEach(player -> {
                if (player instanceof ScreenShaker shaker) {
                    shaker.addScreenShake(3.0f, 20);
                }
            });
        }

        if (monolith.ageTicks == (monolith.secondsToFinalize * 20)) {
            monolith.finalize(world, pos, monolith);
        }
    }

    private void finalize(World world, BlockPos pos, @NotNull CovetousMonolithBlockEntity monolithBlockEntity) {
        world.getPlayers().forEach(player -> {
            if (player instanceof ScreenShaker shaker) {
                shaker.addScreenShake(3.0f, 20);
            }
        });

        world.setBlockState(pos, Blocks.AIR.getDefaultState());

        List<Entity> targetsAsEntity = ModUtils.getEntitiesAroundPos(pos, world, 75);

        for (Entity entity : targetsAsEntity) {
            if (entity instanceof LivingEntity living && !(living instanceof PlayerEntity)) {
                ModUtils.teleportToAsphodel(living);
            }

            if (entity instanceof PlayerEntity player) {
                if (!player.getInventory().contains(SaxoItems.DEIFIC_WARRANT.getDefaultStack())) {
                    ModUtils.teleportToAsphodel(player);
                } else {
                    //
                }
            }
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
