package net.chemthunder.saxophone.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.chemthunder.saxophone.impl.Saxophone;
import net.chemthunder.saxophone.impl.util.ModUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.util.Identifier;
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
    private void saxo$asphodelSun(int texture, Identifier id, Operation<Void> original) {
        original.call(texture, ModUtils.isInAsphodel(MinecraftClient.getInstance().player) ? Saxophone.id("textures/environment/asphodel_sun.png") : id);
    }
}
