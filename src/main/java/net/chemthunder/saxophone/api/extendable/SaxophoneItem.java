package net.chemthunder.saxophone.api.extendable;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SaxophoneItem extends Item implements ColorableItem {
    public SaxophoneItem(Settings settings) {
        super(settings);
    }

    public int startColor(ItemStack itemStack) {
        return 0xFFd70048;
    }
    public int endColor(ItemStack itemStack) {
        return 0xFF8e1a41;
    }
    public int backgroundColor(ItemStack itemStack) {return 0xFF1c0810;}
}
