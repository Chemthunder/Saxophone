package net.chemthunder.saxophone.impl.index;

import net.acoyt.acornlib.api.registrants.ItemGroupRegistrant;
import net.chemthunder.saxophone.impl.Saxophone;
import net.chemthunder.saxophone.impl.util.ModUtils;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

public interface SaxoItemGroups {
    ItemGroupRegistrant ITEM_GROUPS = new ItemGroupRegistrant(Saxophone.MOD_ID);

    MutableText text = Text.translatable("itemGroup.saxophone");
    ItemGroup MAIN = FabricItemGroup.builder()
            .icon(() -> new ItemStack(SaxoItems.LIBERATION))
            .displayName(text.withColor(0xFFd70048))
            .build();

    static void init() {
        ITEM_GROUPS.register("saxophone", MAIN, SaxoItemGroups::buildItemGroup);
    }

    private static void buildItemGroup(FabricItemGroupEntries entries) {
        for (Item item : SaxoItems.ITEMS.toRegister) {
            entries.add(item);
        }
    }
}
