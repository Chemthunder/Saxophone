package net.chemthunder.saxophone.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.chemthunder.saxophone.impl.util.ModUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
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
        return original;
    }
}