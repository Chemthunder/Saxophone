package net.chemthunder.saxophone.impl.effect;

import com.nitron.nitrogen.util.interfaces.StatusEffectBackground;
import net.chemthunder.saxophone.impl.Saxophone;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;

public class SaxophoneBackgroundStatusEffect extends StatusEffect implements StatusEffectBackground {
    public SaxophoneBackgroundStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    public Identifier smallInventorySprite() {
        return Saxophone.id("container/inventory/insis_bg_small");
    }

    public Identifier largeInventorySprite() {
        return Saxophone.id("container/inventory/insis_bg_large");
    }

    public Identifier hudSprite() {
        return Saxophone.id("hud/insis_bg");
    }
}
