package net.chemthunder.saxophone.impl.util;

import com.everest.hibiscus.api.modules.rendering.text.HibiscusPresetEffects;
import com.everest.hibiscus.api.modules.rendering.text.registry.TextEffectManager;
import net.chemthunder.saxophone.impl.cca.entity.AvariceComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

public class ModUtils {

    public static boolean isAvarice(PlayerEntity player) {
        return AvariceComponent.KEY.get(player).isAvarice();
    }

    public static Style nameEffect(MutableText text) {
        return TextEffectManager.withEffect(text.getStyle(), HibiscusPresetEffects.DOUBLE_LERP_WAVE_EFFECT, TextEffectManager.getEffect(HibiscusPresetEffects.DOUBLE_LERP_WAVE_EFFECT));
    }

    public static Style nameEffect(Text text) {
        return TextEffectManager.withEffect(text.getStyle(), HibiscusPresetEffects.DOUBLE_LERP_WAVE_EFFECT, TextEffectManager.getEffect(HibiscusPresetEffects.DOUBLE_LERP_WAVE_EFFECT));
    }
}
