package net.chemthunder.reverence.impl.index;

import net.acoyt.acornlib.api.registrants.ItemRegistrant;
import net.chemthunder.reverence.impl.Reverence;
import net.chemthunder.reverence.impl.item.EmptinessItem;
import net.minecraft.item.Item;

public interface ReverenceItems {
    ItemRegistrant ITEMS = new ItemRegistrant(Reverence.MOD_ID);

    Item EMPTINESS = ITEMS.register("emptiness", EmptinessItem::new, new Item.Settings().maxCount(1).attributeModifiers(EmptinessItem.createAttributeModifiers()));

    static void init() {}
}
