package net.chemthunder.saxophone.core;

import net.chemthunder.saxophone.api.event.WindowTitleEvent;
import net.chemthunder.saxophone.core.client.render.block.CovetousMonolithBlockEntityRenderer;
import net.chemthunder.saxophone.core.index.SaxoBlockEntities;
import net.chemthunder.saxophone.core.index.SaxoEntities;
import net.chemthunder.saxophone.core.index.SaxoNetworking;
import net.chemthunder.saxophone.core.index.SaxoParticles;
import net.chemthunder.saxophone.core.util.ModUtils;
import net.chemthunder.saxophone.core.util.keybinds.SaxophoneKeybindings;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.util.Identifier;
import org.ladysnake.satin.api.event.EntitiesPreRenderCallback;
import org.ladysnake.satin.api.event.ShaderEffectRenderCallback;
import org.ladysnake.satin.api.managed.ManagedCoreShader;
import org.ladysnake.satin.api.managed.ShaderEffectManager;
import org.ladysnake.satin.api.managed.uniform.Uniform1f;

/**
 * @author Chemthunder
 */
public class SaxophoneClient implements ClientModInitializer {

    //Shader management c/o Nightstrike
    public static final ManagedCoreShader transientEffect = ShaderEffectManager
            .getInstance()
            .manageCoreShader(
                    Identifier.of(Saxophone.MOD_ID, "transient")
            );
    public Uniform1f t = transientEffect.findUniform1f("STime");
    public Uniform1f r = transientEffect.findUniform1f("Randomizer");
    public int tick;

    //
    public void onInitializeClient() {
        ShaderEffectRenderCallback.EVENT.register(td -> {
            //TO-DO: Client screen effects
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> tick++);
        EntitiesPreRenderCallback.EVENT.register(
                (c, f, td) -> {
                    t.set((tick + td) * 0.05f);
                    r.set((float) (Math.random()));
                }
        );
        //------------------------

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
