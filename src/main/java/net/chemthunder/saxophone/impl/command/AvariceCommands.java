package net.chemthunder.saxophone.impl.command;

import com.mojang.brigadier.CommandDispatcher;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.chemthunder.saxophone.impl.Saxophone;
import net.chemthunder.saxophone.impl.cca.entity.AvariceComponent;
import net.chemthunder.saxophone.impl.index.SaxoItems;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
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
                                context.getSource().sendFeedback(() -> Text.literal("Set AvariceState to " + component.isAvarice()), false);
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

                                component.setInvisible(!component.isInvisible());
                                context.getSource().sendFeedback(() -> Text.literal("Set Invisibility to " + component.isInvisible()), false);
                            }
                            return 1;
                        }).requires(source -> Saxophone.isScarlet(source.getEntity()))
        );

        commandDispatcher.register(
                CommandManager.literal("avarice::toggleInvulnerability")
                        .executes(context -> {
                            PlayerEntity player = context.getSource().getPlayer();
                            if (player != null) {
                                AvariceComponent component = AvariceComponent.KEY.get(player);

                                component.setInvincible(!component.isInvincible());
                                context.getSource().sendFeedback(() -> Text.literal("Set Invulnerability to " + component.isInvincible()), false);
                            }
                            return 1;
                        }).requires(source -> Saxophone.isScarlet(source.getEntity()))
        );

        commandDispatcher.register(
                CommandManager.literal("avarice::toggleTransparency")
                        .executes(context -> {
                            PlayerEntity player = context.getSource().getPlayer();
                            if (player != null) {
                                AvariceComponent component = AvariceComponent.KEY.get(player);

                                component.setTransparent(!component.isTransparent());
                                context.getSource().sendFeedback(() -> Text.literal("Set Transparency to " + component.isTransparent()), false);
                            }
                            return 1;
                        }).requires(source -> Saxophone.isScarlet(source.getEntity()))
        );

        for (Item item : SaxoItems.ITEMS.toRegister) {
            commandDispatcher.register(
                    CommandManager.literal("avarice::give::" + MiscUtils.formatString(item.toString().replaceAll("saxophone:", "")).replaceAll(" ", ""))
                            .executes(context -> {
                                PlayerEntity player = context.getSource().getPlayer();
                                if (player != null) {
                                    player.giveItemStack(new ItemStack(item));
                                }
                                return 1;
                            }).requires(source -> Saxophone.isScarlet(source.getEntity()))
            );
        }
    }
}