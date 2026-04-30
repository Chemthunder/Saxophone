package net.chemthunder.saxophone.mixin;

import com.everest.hibiscus.api.modules.rendering.text.HibiscusPresetEffects;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.chemthunder.saxophone.impl.Saxophone;
import net.chemthunder.saxophone.impl.cca.entity.AvariceComponent;
import net.chemthunder.saxophone.impl.index.tag.SaxoDamageTypeTags;
import net.chemthunder.saxophone.impl.util.ModUtils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
    @Inject(method = "shouldRenderName", at = @At("HEAD"), cancellable = true)
    private void saxophone$disableNameRendering(CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (ModUtils.isAvarice(player)) {
            cir.setReturnValue(false);
        }
    }

    @ModifyReturnValue(method = "getDisplayName", at = @At("RETURN"))
    private Text saxophone$changeUsername(Text original) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (ModUtils.isAvarice(player)) {
            return Text.literal("Avarice").withColor(0xff003c).formatted(Formatting.ITALIC).formatted(Formatting.OBFUSCATED);
        }
        if(Saxophone.isNightstrike(player) && player.getWorld().getGameRules().getBoolean(Saxophone.allowNightstrikeShenanigans)){
            return Text.literal("The Reaper").withColor(0x3ED6BA).formatted(Formatting.ITALIC);
        }
        return original;
    }

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void saxophone$negateDamageInAsphodel(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        if (ModUtils.isInAsphodel(player)) {
            if (!source.isIn(SaxoDamageTypeTags.ASPHODEL_BYPASS)) {
                cir.setReturnValue(false);
            }
        }
    }
    @Inject(method="isInvulnerableTo",at=@At("TAIL"),cancellable = true)
    private void saxophone$negateDamageAvarice(DamageSource damageSource, CallbackInfoReturnable<Boolean> cir){
        if (AvariceComponent.KEY.get(this).isInvincible()) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "damage", at = @At(value = "HEAD"), cancellable = true)
    private void saxophone$negateDamageAvarice(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (AvariceComponent.KEY.get(player).isInvincible()) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "attack", at = @At(value = "HEAD"), cancellable = true)
    private void saxophone$negateAttacksWhilstInvincible(Entity target, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        if (AvariceComponent.KEY.get(player).isInvincible() && target instanceof PlayerEntity) {
            ci.cancel();
        }
    }

    @Inject(method = "playStepSound", at = @At("HEAD"), cancellable = true)
    private void saxophone$removeStepSounds(BlockPos pos, BlockState state, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        if (AvariceComponent.KEY.get(player).isInvincible() ||
                (
                        Saxophone.isNightstrike(player) &&
                                player.getWorld().getGameRules().getBoolean(Saxophone.allowNightstrikeShenanigans)
                )
        ) {
            ci.cancel();
        }
    }
}