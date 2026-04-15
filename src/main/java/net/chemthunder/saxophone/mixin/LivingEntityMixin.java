package net.chemthunder.saxophone.mixin;

import net.chemthunder.saxophone.impl.cca.entity.AvariceComponent;
import net.chemthunder.saxophone.impl.index.SaxoItems;
import net.chemthunder.saxophone.impl.util.ModUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
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
                if (ModUtils.hasItemInHands(player, SaxoItems.REVENANT_EFFIGY)) {
                    cir.setReturnValue(true);
                }
            }
        }
    }
}
