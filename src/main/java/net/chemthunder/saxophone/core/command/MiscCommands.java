package net.chemthunder.saxophone.core.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.chemthunder.saxophone.core.util.ModUtils;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;

public class MiscCommands implements CommandRegistrationCallback {
    public void register(CommandDispatcher<ServerCommandSource> commandDispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        commandDispatcher.register(CommandManager.literal("spawn").executes(context -> {
            PlayerEntity player = context.getSource().getPlayer();

            if (player != null) {
                World world = player.getWorld();
                BlockPos spawnPos = world.getSpawnPos();

                if (world instanceof ServerWorld serverWorld) {
                    Vec3d pos = new Vec3d(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());

                    if (!ModUtils.isInAsphodel(player)) {
                        player.teleportTo(new TeleportTarget(serverWorld, pos, player.getVelocity(), player.getYaw(), player.getPitch(), TeleportTarget.NO_OP));
                    }
                }
            }

            return Command.SINGLE_SUCCESS;
        }));
    }
}
