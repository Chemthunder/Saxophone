package net.chemthunder.saxophone.mixin;

import net.chemthunder.saxophone.impl.cca.entity.AvariceComponent;
import net.chemthunder.saxophone.impl.cca.entity.InsistenceComponent;
import net.chemthunder.saxophone.impl.cca.entity.RevenantDeathAnimationComponent;
import net.chemthunder.saxophone.impl.index.SaxoItems;
import net.chemthunder.saxophone.impl.item.AuthoritysObituaryItem;
import net.chemthunder.saxophone.impl.util.ModUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(method = "tryUseTotem", at = @At("HEAD"), cancellable = true)
    private void saxo$revenantEffigy(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity living = (LivingEntity) (Object) this;

        if (living instanceof PlayerEntity player) {
            AvariceComponent avarice = AvariceComponent.KEY.get(player);

            if (!avarice.isAvarice()) {
                if (ModUtils.hasItemInHands(player, SaxoItems.REVENANT_EFFIGY) && !RevenantDeathAnimationComponent.KEY.get(player).animationIsActive()) {
                    cir.setReturnValue(true);
                }
            }
        }
    }

    @Inject(method = "tryUseTotem", at = @At("HEAD"), cancellable = true)
    private void saxo$authorityObituary(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity living = (LivingEntity) (Object) this;

        if (source.getAttacker() instanceof PlayerEntity player) {
            if (player.getOffHandStack().getItem() instanceof AuthoritysObituaryItem i) {
                cir.setReturnValue(true);
                i.getKillEffect(player, living, player.getOffHandStack(), player.getWorld());
            }
        }
    }

    @Inject(method = "applyMovementInput", at = @At("HEAD"), cancellable = true)
    private void saxo$cancelMovement(Vec3d movementInput, float slipperiness, CallbackInfoReturnable<Vec3d> cir) {
        if ((Object) this instanceof LivingEntity player) {
            if (InsistenceComponent.KEY.get(player).getActiveTicks() > 0) {
                cir.setReturnValue(Vec3d.ZERO);
            }
        }
    }

    @Inject(method = "applyFluidMovingSpeed", at = @At("HEAD"), cancellable = true)
    private void saxo$cancelFluidMovement(double gravity, boolean falling, Vec3d motion, CallbackInfoReturnable<Vec3d> cir) {
        if ((Object) this instanceof LivingEntity player) {
            if (InsistenceComponent.KEY.get(player).getActiveTicks() > 0) {
                cir.setReturnValue(Vec3d.ZERO);
            }
        }
    }
}