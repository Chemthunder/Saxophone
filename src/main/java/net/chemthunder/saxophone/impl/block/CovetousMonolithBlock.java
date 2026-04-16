package net.chemthunder.saxophone.impl.block;

import com.mojang.serialization.MapCodec;
import net.chemthunder.saxophone.impl.block.entity.CovetousMonolithBlockEntity;
import net.chemthunder.saxophone.impl.index.SaxoBlockEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CovetousMonolithBlock extends BlockWithEntity {
    public static final MapCodec<CovetousMonolithBlock> CODEC = createCodec(CovetousMonolithBlock::new);

    public CovetousMonolithBlock(Settings settings) {
        super(settings);
    }

    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CovetousMonolithBlockEntity(pos, state);
    }

    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, SaxoBlockEntities.COVETOUS_MONOLITH, CovetousMonolithBlockEntity::tick);
    }

    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}