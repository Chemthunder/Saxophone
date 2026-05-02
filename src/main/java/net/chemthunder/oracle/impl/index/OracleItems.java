package net.chemthunder.oracle.impl.index;

import net.acoyt.acornlib.api.registrants.ItemRegistrant;
import net.chemthunder.oracle.impl.Oracle;
import net.chemthunder.oracle.impl.item.SalvationItem;
import net.minecraft.item.Item;

public interface OracleItems {
    ItemRegistrant ITEMS = new ItemRegistrant(Oracle.MOD_ID);

    Item SALVATION = ITEMS.register("salvation", SalvationItem::new, new Item.Settings().maxCount(1).attributeModifiers(SalvationItem.createAttributeModifiers()));

    static void init() {}
}
