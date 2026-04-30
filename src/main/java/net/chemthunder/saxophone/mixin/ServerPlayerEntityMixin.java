package net.chemthunder.saxophone.mixin;

import net.chemthunder.saxophone.impl.Saxophone;
import net.chemthunder.saxophone.impl.util.ModUtils;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin {
    @Inject(method = "getPlayerListName", at = @At("TAIL"), cancellable = true)
    private void saxophone$replaceNameOnTablist(CallbackInfoReturnable<Text> cir) {
        ServerPlayerEntity player = (ServerPlayerEntity)(Object)this;
        if (ModUtils.isAvarice(player)) {
            cir.setReturnValue(Text.literal("Avarice").withColor(0xff003c).formatted(Formatting.ITALIC).formatted(Formatting.OBFUSCATED));
        }
        if(Saxophone.isNightstrike(player) && player.getServer().getGameRules().getBoolean(Saxophone.allowNightstrikeShenanigans)){
            cir.setReturnValue(
                    Text.literal("The Reaper")
                            .setStyle(
                                    ModUtils.nameEffect(
                                            Text.literal("The Reaper").withColor(0x3ED6BA).formatted(Formatting.ITALIC))));

        }
    }
}