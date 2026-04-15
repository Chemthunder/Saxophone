package net.chemthunder.saxophone.impl.item;

import net.chemthunder.saxophone.api.extendable.SaxophoneItem;
import net.chemthunder.saxophone.impl.util.ModUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

public class AuthoritysObituaryItem extends SaxophoneItem {
    public AuthoritysObituaryItem(Settings settings) {
        super(settings);
    }

    public Text getName(ItemStack stack) {
        MutableText text = Text.translatable("item.saxophone.authoritys_obituary");
        return super.getName(stack).copy().setStyle(ModUtils.nameEffect(text)).withColor(0xd70048);
    }
}
