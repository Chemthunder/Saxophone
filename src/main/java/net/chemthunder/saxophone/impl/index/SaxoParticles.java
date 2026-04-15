package net.chemthunder.saxophone.impl.index;

import net.chemthunder.saxophone.impl.Saxophone;
import net.chemthunder.saxophone.impl.client.particle.ShockwaveParticle;
import net.chemthunder.saxophone.impl.client.particle.ShockwaveParticleEffect;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public interface SaxoParticles {
    ParticleType<ShockwaveParticleEffect> SHOCKWAVE = create("shockwave", FabricParticleTypes.complex(true, ShockwaveParticleEffect.CODEC, ShockwaveParticleEffect.PACKET_CODEC));

    private static <T extends ParticleType<?>> T create(String name, T particle) {
        return Registry.register(Registries.PARTICLE_TYPE, Saxophone.id(name), particle);
    }

    static void init() {}

    static void clientInit() {
        ParticleFactoryRegistry.getInstance().register(SHOCKWAVE, ShockwaveParticle.Factory::new);
    }
}
