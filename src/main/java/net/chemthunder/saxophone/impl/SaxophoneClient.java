package net.chemthunder.saxophone.impl;

import net.chemthunder.saxophone.api.event.WindowTitleEvent;
import net.chemthunder.saxophone.impl.client.render.block.CovetousMonolithBlockEntityRenderer;
import net.chemthunder.saxophone.impl.index.SaxoBlockEntities;
import net.chemthunder.saxophone.impl.index.SaxoEntities;
import net.chemthunder.saxophone.impl.index.SaxoNetworking;
import net.chemthunder.saxophone.impl.index.SaxoParticles;
import net.chemthunder.saxophone.impl.util.ModUtils;
import net.chemthunder.saxophone.impl.util.keybinds.SaxophoneKeybindings;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

/**
 * @author Chemthunder
 */
public class SaxophoneClient implements ClientModInitializer {
    public void onInitializeClient() {
        SaxoEntities.clientInit();
        SaxoParticles.clientInit();

        SaxophoneKeybindings.register();

        SaxoNetworking.registerS2CPackets();

        BlockEntityRendererFactories.register(SaxoBlockEntities.COVETOUS_MONOLITH, CovetousMonolithBlockEntityRenderer::new);

        // window titles
        WindowTitleEvent.register(
                "let's play a game, friend.",
                client -> ModUtils.isInAsphodel(client.player)
        );

        WindowTitleEvent.init();
    }


}
