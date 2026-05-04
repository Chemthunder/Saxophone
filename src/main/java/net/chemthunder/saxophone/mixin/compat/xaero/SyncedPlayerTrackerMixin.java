package net.chemthunder.saxophone.mixin.compat.xaero;

import net.chemthunder.saxophone.impl.util.ModUtils;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xaero.common.server.radar.tracker.SyncedPlayerTracker;
import xaero.common.server.radar.tracker.SyncedTrackedPlayer;

import java.util.UUID;


@Mixin(SyncedPlayerTracker.class)
public abstract class SyncedPlayerTrackerMixin {

    @Shadow protected abstract void sendRemovePacket(ServerPlayerEntity player, UUID toRemove);

    @Inject(method="sendTrackedPlayerPacket",at=@At("HEAD"),cancellable = true)
    public void suspendDeityTracking(ServerPlayerEntity player, SyncedTrackedPlayer tracked, CallbackInfo ci){
        if (ModUtils.isAvarice(player)){
            sendRemovePacket(player, player.getUuid());
            ci.cancel();
        }else if(ModUtils.isEos(player)){
            sendRemovePacket(player,player.getUuid());
            ci.cancel();
        }
    }
}
