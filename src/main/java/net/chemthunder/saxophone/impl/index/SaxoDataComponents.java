package net.chemthunder.saxophone.impl.index;

import net.acoyt.acornlib.api.registrants.ComponentTypeRegistrant;
import net.chemthunder.saxophone.impl.Saxophone;
import net.chemthunder.saxophone.impl.component.ContractComponent;
import net.minecraft.component.ComponentType;

public interface SaxoDataComponents {
    ComponentTypeRegistrant DATA_COMPONENTS = new ComponentTypeRegistrant(Saxophone.MOD_ID);

    ComponentType<ContractComponent> CONTRACT = DATA_COMPONENTS.register("contract", builder -> builder.codec(ContractComponent.CODEC));

    static void init() {}
}