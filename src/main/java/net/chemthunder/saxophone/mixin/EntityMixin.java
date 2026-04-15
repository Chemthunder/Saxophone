package net.chemthunder.saxophone.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.chemthunder.saxophone.impl.cca.entity.ForsakenCharterComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @ModifyReturnValue(method = "getTeamColorValue", at = @At("RETURN"))
    private int saxo$customTeamColors(int original) {
        Entity entity = (Entity) (Object) this;

        if (entity instanceof LivingEntity living) {
            ForsakenCharterComponent component = ForsakenCharterComponent.KEY.get(living);

            if (component.isInBox()) {
                return 0xFFd70048;
            }
        }
        return original;
    }
}
