package net.chemthunder.saxophone.impl.util;

import com.everest.hibiscus.api.modules.rendering.text.HibiscusPresetEffects;
import com.everest.hibiscus.api.modules.rendering.text.registry.TextEffectManager;
import net.chemthunder.saxophone.impl.Saxophone;
import net.chemthunder.saxophone.impl.cca.entity.AvariceComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;

import java.util.List;

public class ModUtils {
    public static boolean isAvarice(PlayerEntity player) {
        return AvariceComponent.KEY.get(player).isAvarice();
    }

    public static Style nameEffect(MutableText text) {
        return TextEffectManager.withEffect(text.getStyle(), HibiscusPresetEffects.DOUBLE_LERP_WAVE_EFFECT, TextEffectManager.getEffect(HibiscusPresetEffects.DOUBLE_LERP_WAVE_EFFECT));
    }

    public static Style nameEffect(Text text) {
        return TextEffectManager.withEffect(text.getStyle(), HibiscusPresetEffects.DOUBLE_LERP_WAVE_EFFECT, TextEffectManager.getEffect(HibiscusPresetEffects.DOUBLE_LERP_WAVE_EFFECT));
    }

    public static List<Entity> getEntitiesAroundEntity(Entity living, int expansion) {
        return living.getWorld().getEntitiesByClass(Entity.class, new Box(living.getBlockPos()).expand(expansion), entity -> true);
    }

    public static RegistryKey<World> asphodelKey = RegistryKey.of(RegistryKeys.WORLD, Saxophone.id("asphodel"));

    public static boolean isInAsphodel(LivingEntity living) {
        return living != null && living.getWorld().getRegistryKey() == asphodelKey;
    }

    public static ServerWorld getAsphodel(MinecraftServer server) {
        return server.getWorld(asphodelKey);
    }

    public static void teleportToAsphodel(LivingEntity living) {
        Vec3d pos = new Vec3d(living.getX(), -319, living.getZ());

        if (living.getServer() != null) {
            living.teleportTo(new TeleportTarget(getAsphodel(living.getServer()), pos, living.getVelocity(), living.getYaw(), living.getPitch(), TeleportTarget.NO_OP));
        }
    }

    public static boolean hasItemInHands(PlayerEntity player, Item item) {
        return player.getMainHandStack().isOf(item) || player.getOffHandStack().isOf(item);
    }
}