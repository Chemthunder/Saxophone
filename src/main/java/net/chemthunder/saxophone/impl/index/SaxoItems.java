package net.chemthunder.saxophone.impl.index;

import net.acoyt.acornlib.api.registrants.ItemRegistrant;
import net.chemthunder.saxophone.impl.Saxophone;
import net.chemthunder.saxophone.impl.item.*;
import net.minecraft.item.Item;

public interface SaxoItems {
    ItemRegistrant ITEMS = new ItemRegistrant(Saxophone.MOD_ID);

    Item LIBERATION = ITEMS.register("liberation", LiberationItem::new, new Item.Settings().maxCount(1).attributeModifiers(LiberationItem.createAttributeModifiers()));
    Item CONTRACT = ITEMS.register("contract", ContractItem::new, new Item.Settings().maxCount(1));
    Item DEIFIC_WARRANT = ITEMS.register("deific_warrant", DeificWarrantItem::new, new Item.Settings().maxCount(1));
    Item FORSAKEN_CHARTER = ITEMS.register("forsaken_charter", ForsakenCharterItem::new, new Item.Settings().maxCount(1));
    Item VIRTUS_DEI = ITEMS.register("virtus_dei", VirtusDeiItem::new, new Item.Settings().maxCount(1));

    static void init() {}
}