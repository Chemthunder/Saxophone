package net.chemthunder.saxophone.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.chemthunder.saxophone.impl.Saxophone;
import net.chemthunder.saxophone.impl.index.SaxoStatusEffects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractInventoryScreen.class)
public abstract class AbstractInventoryScreenMixin {

    @WrapOperation(method = "getStatusEffectDescription", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/effect/StatusEffect;getName()Lnet/minecraft/text/Text;"))
    private Text saxo$madness(StatusEffect instance, Operation<Text> original) {
        PlayerEntity player = MinecraftClient.getInstance().player;

        if (player != null) {
            if (instance.equals(SaxoStatusEffects.MADNESS)) {
                return Text.literal("Madness.").withColor(Saxophone.modColor).formatted(Formatting.ITALIC);
            }

            if (instance.equals(SaxoStatusEffects.REPENTANCE)) {
                return Text.literal("Repentance.").withColor(Saxophone.modColor).formatted(Formatting.ITALIC);
            }
        }

        return original.call(instance);
    }
}
