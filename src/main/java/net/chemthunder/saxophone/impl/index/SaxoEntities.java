package net.chemthunder.saxophone.impl.index;

import net.acoyt.acornlib.api.registrants.EntityTypeRegistrant;
import net.chemthunder.saxophone.impl.Saxophone;
import net.chemthunder.saxophone.impl.client.render.entity.ForsakenCharterEntityRenderer;
import net.chemthunder.saxophone.impl.entity.ForsakenCharterEntity;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;

/**
 * @author Chemthunder
 */
@SuppressWarnings("deprecation")
public interface SaxoEntities {
    EntityTypeRegistrant ENTITIES = new EntityTypeRegistrant<>(Saxophone.MOD_ID);

    EntityType<ForsakenCharterEntity> FORSAKEN_CHARTER = ENTITIES.register("forsaken_charter",
            EntityType.Builder.create(
                    ForsakenCharterEntity::new,
                    SpawnGroup.MISC
            ).dimensions(0.4f, 0.9f).disableSummon()
    );

    static void init() {}

    static void clientInit() {
        EntityRendererRegistry.register(FORSAKEN_CHARTER, ForsakenCharterEntityRenderer::new);
    }
}