package net.chemthunder.reverence.impl.index;

import net.acoyt.acornlib.api.registrants.ItemGroupRegistrant;
import net.chemthunder.reverence.impl.Reverence;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

public interface ReverenceItemGroups {
    ItemGroupRegistrant ITEM_GROUPS = new ItemGroupRegistrant(Reverence.MOD_ID);

    MutableText text = Text.translatable("itemGroup.reverence");
    ItemGroup MAIN = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ReverenceItems.EMPTINESS))
            .displayName(text.withColor(0xFFe567db))
            .build();

    static void init() {
        ITEM_GROUPS.register("reverence", MAIN, ReverenceItemGroups::buildItemGroup);
    }

    private static void buildItemGroup(FabricItemGroupEntries entries) {
        entries.add(ReverenceItems.EMPTINESS);
    }
}
