package net.chemthunder.saxophone.impl.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record ContractComponent(String signerName, boolean isSigned) {
    public static final Codec<ContractComponent> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.optionalFieldOf("signerName", "").forGetter(ContractComponent::signerName),
                    Codec.BOOL.optionalFieldOf("isSigned", false).forGetter(ContractComponent::isSigned)
            ).apply(instance, ContractComponent::new)
    );
}
