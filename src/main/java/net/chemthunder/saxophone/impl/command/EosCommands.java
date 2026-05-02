package net.chemthunder.saxophone.impl.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.chemthunder.saxophone.impl.Saxophone;
import net.chemthunder.saxophone.impl.cca.deity.AvariceComponent;
import net.chemthunder.saxophone.impl.cca.deity.EosComponent;
import net.chemthunder.saxophone.impl.cca.world.AvariceEventComponent;
import net.chemthunder.saxophone.impl.util.ModUtils;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class EosCommands implements CommandRegistrationCallback {
    public void register(CommandDispatcher<ServerCommandSource> commandDispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        commandDispatcher.register(
                CommandManager.literal("eos")
                        .then(CommandManager.literal("dev")
                                .then(CommandManager.literal("query").executes(context -> {
                                    World world = context.getSource().getWorld();
                                    PlayerEntity src = context.getSource().getPlayer();

                                    if (src != null) {
                                        world.getPlayers().forEach(playerEntity -> {
                                            if (ModUtils.isAvarice(playerEntity)) {
                                                AvariceComponent avariceComponent = AvariceComponent.KEY.get(playerEntity);

                                                src.sendMessage(Text.literal("Viewing ~ " + playerEntity.getNameForScoreboard()));
                                                src.sendMessage(Text.literal("isAvarice: " + avariceComponent.isAvarice()));
                                                src.sendMessage(Text.literal("isInvisible: " + avariceComponent.isInvisible()));
                                                src.sendMessage(Text.literal("isInvincible: " + avariceComponent.isInvincible()));
                                                src.sendMessage(Text.literal("isTransparent: " + avariceComponent.isTransparent()));

                                                Saxophone.LOGGER.info("Finished query of {}, passing on.", playerEntity.getNameForScoreboard());
                                            }
                                        });
                                    }

                                    return Command.SINGLE_SUCCESS;
                                })).requires(EosCommands::isChem)

                                .then(CommandManager.literal("disableAll").executes(context -> {
                                    World world = context.getSource().getWorld();
                                    PlayerEntity src = context.getSource().getPlayer();

                                    if (world != null) {
                                        world.getPlayers().forEach(playerEntity -> {
                                            if (playerEntity != src) {
                                                AvariceComponent component = AvariceComponent.KEY.get(playerEntity);

                                                if (component.isAvarice()) {
                                                    component.setAvarice(false);
                                                    component.setInvisible(false);
                                                    component.setInvincible(false);
                                                    component.setTransparent(false);
                                                }
                                            }
                                        });

                                        context.getSource().sendFeedback(() -> Text.literal("Successfully de-avariced all non-essential players."), true);
                                    }

                                    return Command.SINGLE_SUCCESS;
                                })).requires(EosCommands::isChem)

                                .then(CommandManager.literal("authorizeDomainExpansion").executes(context -> {
                                    World world = context.getSource().getWorld();

                                    if (world != null) {
                                        AvariceEventComponent component = AvariceEventComponent.KEY.get(world);

                                        component.setCanUseDomainExpansion(!component.isCanUseDomainExpansion());

                                        world.getPlayers().forEach(playerEntity -> {
                                            playerEntity.sendMessage(Text.literal("aight gang scarlet can do shit now, stand by for domain expansion -modding chem"));
                                        });
                                    }

                                    return Command.SINGLE_SUCCESS;
                                })).requires(EosCommands::isChem)
                        ).requires(EosCommands::isChem)

                        .then(CommandManager.literal("state").executes(context -> {
                            PlayerEntity player = context.getSource().getPlayer();

                            if (player != null) {
                                EosComponent eos = EosComponent.KEY.get(player);

                                eos.setEos(!eos.isEos());
                            }
                            return Command.SINGLE_SUCCESS;
                        })).requires(EosCommands::isChem)

                        .then(CommandManager.literal("flight")
                                .then(CommandManager.literal("toggle").executes(context -> {
                                    PlayerEntity player = context.getSource().getPlayer();

                                    if (player != null) {
                                        EosComponent eos = EosComponent.KEY.get(player);

                                        eos.setFlight(!eos.canFly());
                                    }
                                    return Command.SINGLE_SUCCESS;
                                })).requires(EosCommands::isChem)
                                .then(CommandManager.literal("reset").executes(context -> {
                                    PlayerEntity player = context.getSource().getPlayer();

                                    if (player != null) {
                                        EosComponent eos = EosComponent.KEY.get(player);

                                        eos.setFlight(false);
                                    }
                                    return Command.SINGLE_SUCCESS;
                                }).requires(EosCommands::isChem))
                        ).requires(EosCommands::isChem)
        );
    }

    private static boolean isChem(ServerCommandSource source) { // If command block, is ETHOS, or is opped
        return source.getPlayer() == null || Saxophone.isChem(source.getEntity());
    }
}
