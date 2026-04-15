package net.chemthunder.saxophone.impl.index;

import net.acoyt.acornlib.api.registrants.BlockRegistrant;
import net.chemthunder.saxophone.impl.Saxophone;
import net.chemthunder.saxophone.impl.block.AsphodelTerrainBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public interface SaxoBlocks {
    BlockRegistrant BLOCKS = new BlockRegistrant(Saxophone.MOD_ID);

    Block CLOUDED_THOUGHT = BLOCKS.register("clouded_thought", AsphodelTerrainBlock::new, AbstractBlock.Settings.copy(Blocks.BEDROCK));

    static void init() {}
    static void clientInit() {}
}
