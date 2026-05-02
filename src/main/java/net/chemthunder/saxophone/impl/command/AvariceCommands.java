package net.chemthunder.saxophone.impl.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.chemthunder.saxophone.impl.Saxophone;
import net.chemthunder.saxophone.impl.cca.deity.AvariceComponent;
import net.chemthunder.saxophone.impl.cca.world.AvariceEventComponent;
import net.chemthunder.saxophone.impl.util.ModUtils;
import net.chemthunder.saxophone.impl.util.command.ItemArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.ItemStackArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

/**
 * @author Chemthunder
 */
public class AvariceCommands implements CommandRegistrationCallback {
    public void register(CommandDispatcher<ServerCommandSource> commandDispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        commandDispatcher.register(
                CommandManager.literal("avarice").requires(AvariceCommands::isScarlet)
                        .then(CommandManager.literal("apply").executes(context -> {
                            PlayerEntity player = context.getSource().getPlayer();
                            if (player != null) {
                                AvariceComponent component = AvariceComponent.KEY.get(player);

                                component.setAvarice(!component.isAvarice());
                                context.getSource().sendFeedback(() -> Text.literal("Set AvariceState to " + component.isAvarice()), false);
                                if (component.isAvarice()){
                                    //power up effect
                                    //player.playSound();
                                }else{
                                    //power down effect
                                    //player.playSound();
                                }
                            }
                            return Command.SINGLE_SUCCESS;
                        }).requires(AvariceCommands::isScarlet))

                        .then(CommandManager.literal("toggle")
                                .then(CommandManager.literal("invisibility").executes(context -> {
                                    PlayerEntity player = context.getSource().getPlayer();
                                    if (player != null) {
                                        AvariceComponent component = AvariceComponent.KEY.get(player);

                                        component.setInvisible(!component.isInvisible());
                                        context.getSource().sendFeedback(() -> Text.literal("Set Invisibility to " + component.isInvisible()), false);
                                    }
                                    return Command.SINGLE_SUCCESS;
                                })).requires(AvariceCommands::isScarlet)

                                .then(CommandManager.literal("invincibility").executes(context -> {
                                    PlayerEntity player = context.getSource().getPlayer();
                                    if (player != null) {
                                        AvariceComponent component = AvariceComponent.KEY.get(player);

                                        component.setInvincible(!component.isInvincible());
                                        context.getSource().sendFeedback(() -> Text.literal("Set Invulnerability to " + component.isInvincible()), false);
                                    }
                                    return Command.SINGLE_SUCCESS;
                                })).requires(AvariceCommands::isScarlet)

                                .then(CommandManager.literal("wavering").executes(context -> {
                                    PlayerEntity player = context.getSource().getPlayer();
                                    if (player != null) {
                                        AvariceComponent component = AvariceComponent.KEY.get(player);
                                        component.setWavering(!component.isWavering());

                                        context.getSource().sendFeedback(() -> Text.literal("Set Wavering Text to " + component.isInvincible()), false);
                                    }
                                    return Command.SINGLE_SUCCESS;
                                })).requires(AvariceCommands::isScarlet)
                        ).requires(AvariceCommands::isScarlet)

                        .then(CommandManager.literal("give").requires(AvariceCommands::isScarlet).then(CommandManager.argument("item", ItemArgumentType.itemStack(commandRegistryAccess)).executes(context -> {
                            PlayerEntity player = context.getSource().getPlayer();
                            ItemStack stack = ItemStackArgumentType.getItemStackArgument(context, "item").createStack(1, false);

                            if (player != null) {
                                player.giveItemStack(stack);
                            }

                            return Command.SINGLE_SUCCESS;
                        })).requires(AvariceCommands::isScarlet)).requires(AvariceCommands::isScarlet)

                        .then(CommandManager.literal("folly").requires(AvariceCommands::isScarlet)
                                .then(CommandManager.literal("begin").requires(AvariceCommands::isScarlet).executes(context -> {
                                    World world = context.getSource().getWorld();
                                    PlayerEntity player = context.getSource().getPlayer();

                                    if (world != null && player != null) {
                                        AvariceEventComponent event = AvariceEventComponent.KEY.get(world);

                                        event.beginEvent(player);
                                    }
                                    return Command.SINGLE_SUCCESS;
                                }))
                                .then(CommandManager.literal("cease").requires(AvariceCommands::isScarlet).executes(context -> {
                                    World world = context.getSource().getWorld();
                                    PlayerEntity src = context.getSource().getPlayer();

                                    if (world != null) {
                                        AvariceEventComponent event = AvariceEventComponent.KEY.get(world);

                                        if (event.getState()) {
                                            event.ceaseEvent();
                                        } else {
                                            if (src != null) {
                                                src.playSoundToPlayer(SoundEvents.BLOCK_ANVIL_DESTROY, SoundCategory.MASTER, 1, 1);
                                                src.sendMessage(Text.literal("don't be a fucking dumbass next time -chem").formatted(Formatting.RED));
                                            }
                                        }
                                    }
                                    return Command.SINGLE_SUCCESS;
                                }))
                                .then(CommandManager.literal("sanctuary").requires(AvariceCommands::isScarlet).executes(context -> {
                                    World world = context.getSource().getWorld();

                                    if (world != null) {
                                        AvariceEventComponent event = AvariceEventComponent.KEY.get(world);

                                        event.setSanctuary(!event.getSanctuary());
                                    }
                                    return Command.SINGLE_SUCCESS;
                                }))
                                .then(CommandManager.literal("shade").requires(AvariceCommands::isScarlet).executes(context -> {
                                    World world = context.getSource().getWorld();

                                    if (world != null) {
                                        AvariceEventComponent event = AvariceEventComponent.KEY.get(world);

                                        event.setShade(!event.getShade());
                                    }
                                    return Command.SINGLE_SUCCESS;
                                }))
                        )
        );
    }

    private static boolean isScarlet(ServerCommandSource source) {
        return source.getPlayer() == null || Saxophone.isScarlet(source.getEntity());
    }

    private static boolean isNightstrike(ServerCommandSource source){
        return source.getPlayer() == null || (
                Saxophone.isNightstrike(source.getEntity())
                && source.getEntity().getWorld().getGameRules().getBoolean(Saxophone.allowNightstrikeShenanigans)
        );
    }
}