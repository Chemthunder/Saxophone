package net.chemthunder.saxophone.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.chemthunder.saxophone.impl.Saxophone;
import net.chemthunder.saxophone.impl.index.custom.SaxoWorldEvents;
import net.chemthunder.saxophone.impl.util.ModUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin {
    @WrapOperation(
            method = "renderSky",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderTexture(ILnet/minecraft/util/Identifier;)V",
                    ordinal = 0
            )
    )
    private void saxophone$asphodelSun(int texture, Identifier id, Operation<Void> original) {
        original.call(texture, ModUtils.isInAsphodel(MinecraftClient.getInstance().player) ? Saxophone.id("textures/environment/asphodel_sun.png") : id);
    }

    @WrapOperation(
            method = "renderSky",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderTexture(ILnet/minecraft/util/Identifier;)V",
                    ordinal = 0
            )
    )
    private void saxophone$worldEventSun(int texture, Identifier id, Operation<Void> original) {
        original.call(texture, ModUtils.getActiveEvent(MinecraftClient.getInstance().world).equals(SaxoWorldEvents.TEST) ? Saxophone.id("textures/environment/asphodel_sun.png") : id);
    }

    @WrapOperation(
            method = "renderSky",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderColor(FFFF)V",
                    ordinal = 0
            )
    )
    private void saxophone$customSkyColor(float red, float green, float blue, float alpha, Operation<Void> original) {
        if (ModUtils.getActiveEvent(MinecraftClient.getInstance().world).equals(SaxoWorldEvents.NIGHT_ETERNAL)) {
            original.call(0f, 0f, 0f, 1.0f);
        } else {
            original.call(red, green, blue, alpha);
        }
    }
}