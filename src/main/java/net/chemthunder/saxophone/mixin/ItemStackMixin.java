package net.chemthunder.saxophone.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.chemthunder.saxophone.impl.cca.entity.AvariceComponent;
import net.chemthunder.saxophone.impl.util.ModUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Inject(method = "use", at = @At(value = "HEAD"), cancellable = true)
    private void saxo$denyUse(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        if (ModUtils.isInAsphodel(user) && !ModUtils.isAvarice(user)) {
            cir.cancel();
        }
    }

    @Inject(method = "useOnBlock", at = @At(value = "HEAD"), cancellable = true)
    private void saxo$denyUseOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        if (ModUtils.isInAsphodel(context.getPlayer()) && !ModUtils.isAvarice(context.getPlayer())) {
            cir.cancel();
        }
    }

    @Inject(method = "useOnEntity", at = @At(value = "HEAD"), cancellable = true)
    private void saxo$denyUseOnEntity(PlayerEntity user, LivingEntity entity, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (ModUtils.isInAsphodel(user) && !ModUtils.isAvarice(user)) {
            cir.cancel();
        }
    }
}
