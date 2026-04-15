package net.chemthunder.saxophone.impl;

import net.chemthunder.saxophone.impl.index.SaxoEntities;
import net.chemthunder.saxophone.impl.index.SaxoParticles;
import net.fabricmc.api.ClientModInitializer;

public class SaxophoneClient implements ClientModInitializer {
    public void onInitializeClient() {
        SaxoEntities.clientInit();
        SaxoParticles.clientInit();
    }
}