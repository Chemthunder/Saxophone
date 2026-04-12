package net.chemthunder.saxophone.impl.item;

import com.everest.hibiscus.api.modules.rendering.text.HibiscusPresetEffects;
import com.everest.hibiscus.api.modules.rendering.text.registry.TextEffectManager;
import net.acoyt.acornlib.api.item.ModelVaryingItem;
import net.chemthunder.saxophone.api.extendable.SaxophoneItem;
import net.chemthunder.saxophone.impl.Saxophone;
import net.chemthunder.saxophone.impl.component.ContractComponent;
import net.chemthunder.saxophone.impl.index.SaxoDataComponents;
import net.chemthunder.saxophone.impl.util.ModUtils;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class ContractItem extends SaxophoneItem implements ModelVaryingItem {
    public ContractItem(Settings settings) {
        super(settings.component(SaxoDataComponents.CONTRACT, new ContractComponent("", false)));
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (stack != null) {
            if (user.isSneaking()) {
                var component = stack.get(SaxoDataComponents.CONTRACT);

                if (component != null) {
                    if (!component.isSigned()) {
                        stack.set(SaxoDataComponents.CONTRACT, new ContractComponent(user.getNameForScoreboard(), true));

                        if (world.isClient) {
                            user.swingHand(hand);
                        }
                    }
                }
            }
        }
        return super.use(world, user, hand);
    }

    public Identifier getModel(ModelTransformationMode modelTransformationMode, ItemStack itemStack, @Nullable LivingEntity livingEntity) {
        var comp = itemStack.get(SaxoDataComponents.CONTRACT);

        if (comp != null) {
            if (comp.isSigned()) {
                return Saxophone.id("contract_signed");
            }
        }
        return Saxophone.id("contract");
    }

    public List<Identifier> getModelsToLoad() {
        return Arrays.asList(
                Saxophone.id("contract"),
                Saxophone.id("contract_signed")
        );
    }

    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        var comp = stack.get(SaxoDataComponents.CONTRACT);

        if (comp != null) {
            if (comp.isSigned()) {
                MutableText name = Text.literal(comp.signerName());
                tooltip.add(Text.literal("Behold, a bastard named ").formatted(Formatting.DARK_GRAY).append(Text.literal(comp.signerName()).withColor(0xff0055)).append(Text.literal(".").formatted(Formatting.DARK_GRAY)));
                tooltip.add(Text.literal("Let their actions serve as a warning.").formatted(Formatting.DARK_GRAY));
            }
        }

        super.appendTooltip(stack, context, tooltip, type);
    }

    public Text getName(ItemStack stack) {
        var comp = stack.get(SaxoDataComponents.CONTRACT);
        MutableText text;

        if (comp != null) {
            if (!comp.isSigned()) {
                text = Text.translatable("item.saxophone.contract");
            } else {
                text = Text.translatable("item.saxophone.contract_signed");
            }

            return text.setStyle(ModUtils.nameEffect(text)).withColor(0xd70048);
        }

        return super.getName(stack);
    }
}
