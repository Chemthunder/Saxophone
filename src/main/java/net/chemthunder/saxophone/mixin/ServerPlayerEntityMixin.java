package net.chemthunder.saxophone.mixin;

import com.mojang.authlib.GameProfile;
import net.chemthunder.saxophone.core.Saxophone;
import net.chemthunder.saxophone.core.util.ModUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author Chemthunder
 */
@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {
    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    @Inject(method = "getPlayerListName", at = @At("TAIL"), cancellable = true)
    private void saxophone$replaceNameOnTablist(CallbackInfoReturnable<Text> cir) {
        //ServerPlayerEntity player = (ServerPlayerEntity)(Object)this;
        if (ModUtils.isAvarice(this)) {
            cir.setReturnValue(Text.literal("Avarice").withColor(0xff003c).formatted(Formatting.ITALIC).formatted(Formatting.OBFUSCATED));
        }
        if(Saxophone.isNightstrike(this) && this.getServer().getGameRules().getBoolean(Saxophone.allowNightstrikeShenanigans)){
            cir.setReturnValue(
                    Text.literal("The Reaper").withColor(0x3ED6BA).formatted(Formatting.ITALIC));
        }
    }
}