package net.chemthunder.saxophone.impl.index;

import net.acoyt.acornlib.api.registrants.BlockEntityTypeRegistrant;
import net.chemthunder.saxophone.impl.Saxophone;
import net.chemthunder.saxophone.impl.block.entity.CovetousMonolithBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;

@SuppressWarnings("deprecation")
public interface SaxoBlockEntities {
    BlockEntityTypeRegistrant BLOCK_ENTITIES = new BlockEntityTypeRegistrant(Saxophone.MOD_ID);

    BlockEntityType<CovetousMonolithBlockEntity> COVETOUS_MONOLITH = BLOCK_ENTITIES.register(
            "covetous_monolith",
            FabricBlockEntityTypeBuilder.create(CovetousMonolithBlockEntity::new, SaxoBlocks.COVETOUS_MONOLITH)
    );

    static void init() {}
}