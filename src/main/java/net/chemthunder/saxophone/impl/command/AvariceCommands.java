package net.chemthunder.saxophone.impl.command;

import com.mojang.brigadier.CommandDispatcher;
import net.chemthunder.saxophone.impl.Saxophone;
import net.chemthunder.saxophone.impl.cca.entity.AvariceComponent;
import net.chemthunder.saxophone.impl.index.SaxoItems;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class AvariceCommands implements CommandRegistrationCallback {
    public void register(CommandDispatcher<ServerCommandSource> commandDispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        commandDispatcher.register(
                CommandManager.literal("avarice::apply")
                        .executes(context -> {
                            PlayerEntity player = context.getSource().getPlayer();
                            if (player != null) {
                                AvariceComponent component = AvariceComponent.KEY.get(player);

                                component.setAvarice(!component.isAvarice());
                                context.getSource().sendFeedback(() -> Text.literal("Set AvariceState to " + component.isAvarice()), true);
                            }
                            return 1;
                        }).requires(source -> Saxophone.isScarlet(source.getEntity()))
        );

        commandDispatcher.register(
                CommandManager.literal("avarice::toggleInvisibility")
                        .executes(context -> {
                            PlayerEntity player = context.getSource().getPlayer();
                            if (player != null) {
                                AvariceComponent component = AvariceComponent.KEY.get(player);

                                //
                            }
                            return 1;
                        }).requires(source -> Saxophone.isScarlet(source.getEntity()))
        );

        commandDispatcher.register(
                CommandManager.literal("avarice::giveContract")
                        .executes(context -> {
                            PlayerEntity player = context.getSource().getPlayer();
                            if (player != null) {
                                player.giveItemStack(new ItemStack(SaxoItems.CONTRACT));
                            }
                            return 1;
                        }).requires(source -> Saxophone.isScarlet(source.getEntity()))
        );

        commandDispatcher.register(
                CommandManager.literal("avarice::giveLiberation")
                        .executes(context -> {
                            PlayerEntity player = context.getSource().getPlayer();
                            if (player != null) {
                                player.giveItemStack(new ItemStack(SaxoItems.LIBERATION));
                            }
                            return 1;
                        }).requires(source -> Saxophone.isScarlet(source.getEntity()))
        );

        commandDispatcher.register(
                CommandManager.literal("avarice::giveDeificWarrant")
                        .executes(context -> {
                            PlayerEntity player = context.getSource().getPlayer();
                            if (player != null) {
                                player.giveItemStack(new ItemStack(SaxoItems.DEIFIC_WARRANT));
                            }
                            return 1;
                        }).requires(source -> Saxophone.isScarlet(source.getEntity()))
        );

        commandDispatcher.register(
                CommandManager.literal("avarice::giveForsakenCharter")
                        .executes(context -> {
                            PlayerEntity player = context.getSource().getPlayer();
                            if (player != null) {
                                player.giveItemStack(new ItemStack(SaxoItems.FORSAKEN_CHARTER));
                            }
                            return 1;
                        }).requires(source -> Saxophone.isScarlet(source.getEntity()))
        );
        commandDispatcher.register(
                CommandManager.literal("avarice::giveVirtusDei")
                        .executes(context -> {
                            PlayerEntity player = context.getSource().getPlayer();
                            if (player != null) {
                                player.giveItemStack(new ItemStack(SaxoItems.VIRTUS_DEI));
                            }
                            return 1;
                        }).requires(source -> Saxophone.isScarlet(source.getEntity()))
        );
    }
}