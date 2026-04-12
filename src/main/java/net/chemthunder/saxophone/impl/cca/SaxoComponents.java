package net.chemthunder.saxophone.impl.cca;

import net.chemthunder.saxophone.impl.cca.entity.AvariceComponent;
import net.minecraft.entity.player.PlayerEntity;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;

public class SaxoComponents implements EntityComponentInitializer {
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.beginRegistration(PlayerEntity.class, AvariceComponent.KEY).respawnStrategy(RespawnCopyStrategy.ALWAYS_COPY).end(AvariceComponent::new);
    }
}
