package net.chemthunder.saxophone.impl.cca;

import net.chemthunder.saxophone.impl.cca.entity.AvariceComponent;
import net.chemthunder.saxophone.impl.cca.entity.InsistenceComponent;
import net.chemthunder.saxophone.impl.cca.entity.RevenantDeathAnimationComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;

public class SaxoComponents implements EntityComponentInitializer {
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.beginRegistration(PlayerEntity.class, AvariceComponent.KEY).respawnStrategy(RespawnCopyStrategy.ALWAYS_COPY).end(AvariceComponent::new);

        registry.beginRegistration(PlayerEntity.class, RevenantDeathAnimationComponent.KEY).respawnStrategy(RespawnCopyStrategy.NEVER_COPY).end(RevenantDeathAnimationComponent::new);
        registry.beginRegistration(LivingEntity.class, InsistenceComponent.KEY).respawnStrategy(RespawnCopyStrategy.NEVER_COPY).end(InsistenceComponent::new);
    }
}