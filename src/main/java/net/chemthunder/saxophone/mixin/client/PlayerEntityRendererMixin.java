package net.chemthunder.saxophone.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.chemthunder.saxophone.impl.Saxophone;
import net.chemthunder.saxophone.impl.util.ModUtils;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.util.SkinTextures;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin {
    @Inject(
            method = "getTexture(Lnet/minecraft/client/network/AbstractClientPlayerEntity;)Lnet/minecraft/util/Identifier;",
            at = @At("HEAD"),
            cancellable = true
    )
    private void saxophone$changeSkinTexture(AbstractClientPlayerEntity player, CallbackInfoReturnable<Identifier> cir) {
        if (ModUtils.isAvarice(player)) {
            cir.setReturnValue(Saxophone.id("textures/entity/avarice.png"));
        }
    }

    @Redirect(
            method = "renderArm",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;getSkinTextures()Lnet/minecraft/client/util/SkinTextures;"
            )
    )
    private SkinTextures saxophone$keepArmSkinConcurrentToChangedSkin(AbstractClientPlayerEntity instance) {
        SkinTextures defaultTextures = instance.getSkinTextures();
        if (ModUtils.isAvarice(instance)) {
            return new SkinTextures(
                    Saxophone.id("textures/entity/avarice.png"),
                    defaultTextures.textureUrl(),
                    defaultTextures.capeTexture(),
                    defaultTextures.elytraTexture(),
                    SkinTextures.Model.WIDE,
                    defaultTextures.secure()
            );
        }
        return defaultTextures;
    }
}