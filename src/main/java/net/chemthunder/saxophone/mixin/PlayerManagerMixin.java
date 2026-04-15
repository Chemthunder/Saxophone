package net.chemthunder.saxophone.mixin;


import net.minecraft.server.PlayerManager;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PlayerManager.class)
public abstract class PlayerManagerMixin {
//    @WrapWithCondition(
//            method = "onPlayerConnect",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/server/PlayerManager;broadcast(Lnet/minecraft/text/Text;Z)V"
//            )
//    )
//    private boolean saxophone$hideLeaveMessagePlayerManager(PlayerManager instance, Text message, boolean overlay) {
//        return !ModUtils.isAvarice(player);
//    }
}