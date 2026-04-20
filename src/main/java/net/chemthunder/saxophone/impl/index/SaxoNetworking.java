package net.chemthunder.saxophone.impl.index;

import net.chemthunder.saxophone.impl.networking.c2s.ExplodeIvoryPayload;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public interface SaxoNetworking {
    static void registerTypes() {
        PayloadTypeRegistry.playC2S().register(ExplodeIvoryPayload.ID, ExplodeIvoryPayload.CODEC);
    }

    static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(ExplodeIvoryPayload.ID, new ExplodeIvoryPayload.Receiver());
    }

    @Environment(EnvType.CLIENT)
    static void registerS2CPackets() {}
}
