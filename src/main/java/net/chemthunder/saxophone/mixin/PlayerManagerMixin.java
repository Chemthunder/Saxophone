package net.chemthunder.saxophone.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.chemthunder.saxophone.impl.util.ModUtils;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerManager.class)
public abstract class PlayerManagerMixin {
    @WrapWithCondition(
            method = "onPlayerConnect",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/PlayerManager;broadcast(Lnet/minecraft/text/Text;Z)V"
            )
    )
    private boolean saxophone$hideLeaveMessagePlayerManager manager, Text text, boolean bl, ClientConnection connection, ServerPlayerEntity player) {
        return !ModUtils.isAvarice(player);
    }
}