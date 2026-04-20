package net.chemthunder.saxophone.impl;

import net.chemthunder.saxophone.impl.client.render.block.CovetousMonolithBlockEntityRenderer;
import net.chemthunder.saxophone.impl.index.SaxoBlockEntities;
import net.chemthunder.saxophone.impl.index.SaxoEntities;
import net.chemthunder.saxophone.impl.index.SaxoParticles;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class SaxophoneClient implements ClientModInitializer {
    public void onInitializeClient() {
        SaxoEntities.clientInit();
        SaxoParticles.clientInit();

        BlockEntityRendererFactories.register(SaxoBlockEntities.COVETOUS_MONOLITH, CovetousMonolithBlockEntityRenderer::new);
    }
}
