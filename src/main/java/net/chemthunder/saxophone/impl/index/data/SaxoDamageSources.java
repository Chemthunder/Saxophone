package net.chemthunder.saxophone.impl.index.data;

import net.chemthunder.saxophone.impl.Saxophone;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.HashMap;
import java.util.Map;

public interface SaxoDamageSources {
    Map<RegistryKey<DamageType>, String> DAMAGE_SOURCES = new HashMap<>();

    RegistryKey<DamageType> LIBERATE = of("liberate");
    static DamageSource liberate(Entity entity) {
        return entity.getDamageSources().create(LIBERATE);
    }

    RegistryKey<DamageType> AVARICES_WILL = of("avarices_will");
    static DamageSource avaricesWill(Entity entity) {
        return entity.getDamageSources().create(AVARICES_WILL);
    }

    RegistryKey<DamageType> IVORY_EXPLODE = of("ivory_explode");
    static DamageSource ivoryExplode(Entity entity) {
        return entity.getDamageSources().create(IVORY_EXPLODE);
    }

    private static RegistryKey<DamageType> of(String name) {
        RegistryKey<DamageType> key = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Saxophone.id(name));
        DAMAGE_SOURCES.put(key, name);
        return key;
    }

    static void bootstrap(Registerable<DamageType> registerable) {
        DAMAGE_SOURCES.forEach((damageTypeRegistryKey, s) -> registerable.register(damageTypeRegistryKey, new DamageType(s, 0.0f)));
    }
}