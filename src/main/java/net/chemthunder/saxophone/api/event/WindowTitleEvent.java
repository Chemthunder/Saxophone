package net.chemthunder.saxophone.api.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author AcoYT
 */
public class WindowTitleEvent implements ClientTickEvents.EndTick {
    public static final List<WindowTitleEvent> EVENTS = new ArrayList<>();
    private final String name;
    private final Predicate<MinecraftClient> condition;

    public WindowTitleEvent(String name, Predicate<MinecraftClient> condition) {
        this.name = name;
        this.condition = condition;
    }

    public static void register(String name, Predicate<MinecraftClient> condition) {
        EVENTS.add(new WindowTitleEvent(name, condition));
    }

    public static void init() {
        EVENTS.forEach(ClientTickEvents.END_CLIENT_TICK::register);
    }

    public void onEndTick(MinecraftClient client) {
        if (this.condition.test(client)) {
            Window window = MinecraftClient.getInstance().getWindow();
            long windowHandle = window.getHandle();
            GLFW.glfwSetWindowTitle(windowHandle, this.name);
        }
    }
}
