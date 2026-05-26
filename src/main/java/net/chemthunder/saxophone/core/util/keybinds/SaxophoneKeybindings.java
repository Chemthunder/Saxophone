package net.chemthunder.saxophone.core.util.keybinds;

import net.chemthunder.saxophone.core.Saxophone;
import net.chemthunder.saxophone.core.networking.c2s.ExplodeIvoryPayload;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

/**
 * @author Chemthunder
 */
public class SaxophoneKeybindings {
    public static KeyBinding explodeIvory;

    public static void register() {
        registerKeyBindings();
        setupPressDetection();
    }

    private static void registerKeyBindings() {
        String saxophoneCategory = "category.saxophone";
        explodeIvory = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.saxophone.explode_ivory",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UP,
                saxophoneCategory
        ));
    }

    private static void setupPressDetection() {
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if (client.player != null && explodeIvory.isPressed()) {
                handleExplodeIvory(client);
            }
        });
    }

    private static void handleExplodeIvory(MinecraftClient client) {
        if (client.player != null) {
            try {
                ExplodeIvoryPayload.send();
            } catch (Exception e) {
                Saxophone.LOGGER.error("Failed to send Ikir Switch Payload");
            }
        }
    }
}
