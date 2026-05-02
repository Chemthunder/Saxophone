package net.chemthunder.saxophone.mixin;

import net.chemthunder.saxophone.impl.Saxophone;
import net.chemthunder.saxophone.impl.cca.deity.AvariceComponent;
import net.chemthunder.saxophone.impl.cca.entity.InsistenceComponent;
import net.chemthunder.saxophone.impl.cca.entity.RevenantDeathAnimationComponent;
import net.chemthunder.saxophone.impl.item.AuthoritysObituaryItem;
import net.chemthunder.saxophone.impl.item.MartyrdomItem;
import net.chemthunder.saxophone.impl.item.RevenantEffigyItem;
import net.minecraft.entity.Attackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author Chemthunder
 */
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "tryUseTotem", at = @At("HEAD"), cancellable = true)
    private void saxophone$revenantEffigy(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity living = (LivingEntity) (Object) this;

        if (living instanceof PlayerEntity player) {
            AvariceComponent avarice = AvariceComponent.KEY.get(player);

            if (!avarice.isAvarice()) {
                if (player.getOffHandStack().getItem() instanceof RevenantEffigyItem) {
                    if (Saxophone.isScarlet(player)) {
                        if (!RevenantDeathAnimationComponent.KEY.get(player).animationIsActive()) {
                            RevenantDeathAnimationComponent.KEY.get(player).beginAnimation();
                            player.setHealth(player.getMaxHealth());
                            player.getOffHandStack().decrement(1);
                            cir.setReturnValue(true);
                        } else {
                            cir.setReturnValue(true);
                        }
                    }
                }
            }
        }
    }

    @Inject(method = "tryUseTotem", at = @At("HEAD"), cancellable = true)
    private void saxophone$authorityObituary(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity living = (LivingEntity) (Object) this;

        if (source.getAttacker() instanceof PlayerEntity player) {
            if (player.getOffHandStack().getItem() instanceof AuthoritysObituaryItem i) {
                cir.setReturnValue(true);
                i.getKillEffect(player, living, player.getOffHandStack(), player.getWorld());
            }
        }
    }

    @Inject(method = "tryUseTotem", at = @At("HEAD"), cancellable = true)
    private void saxophone$martyrdom(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity living = (LivingEntity) (Object) this;

        if (source.getAttacker() instanceof PlayerEntity player) {
            if (player.getMainHandStack().getItem() instanceof MartyrdomItem i) {
                cir.setReturnValue(true);
                i.getPermakillEffect(player, living, player.getWorld());
            }
        }
    }

    @Inject(method = "applyMovementInput", at = @At("HEAD"), cancellable = true)
    private void saxophone$cancelMovement(Vec3d movementInput, float slipperiness, CallbackInfoReturnable<Vec3d> cir) {
        LivingEntity living = (LivingEntity) (Object) this;
        if (InsistenceComponent.KEY.get(living).getActiveTicks() > 0) {
            cir.setReturnValue(Vec3d.ZERO);
        }
    }

    @Inject(method = "applyFluidMovingSpeed", at = @At("HEAD"), cancellable = true)
    private void saxophone$cancelFluidMovement(double gravity, boolean falling, Vec3d motion, CallbackInfoReturnable<Vec3d> cir) {
        LivingEntity living = (LivingEntity) (Object) this;
        if (InsistenceComponent.KEY.get(living).getActiveTicks() > 0) {
            cir.setReturnValue(Vec3d.ZERO);
        }
    }
}