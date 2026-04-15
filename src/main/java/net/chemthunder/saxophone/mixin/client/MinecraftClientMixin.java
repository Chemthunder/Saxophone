package net.chemthunder.saxophone.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.chemthunder.saxophone.impl.cca.entity.ForsakenCharterComponent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @ModifyReturnValue(method = "hasOutline", at = @At("RETURN"))
    private boolean saxo$glowsIfInForsakenCharterDistance(boolean original) {
        Entity entity = MinecraftClient.getInstance().getCameraEntity();

        if (entity instanceof LivingEntity living) {
            ForsakenCharterComponent comp = ForsakenCharterComponent.KEY.get(living);

            if (comp.isInBox()) {
                return true;
            }
        }

        return original;
    }
}