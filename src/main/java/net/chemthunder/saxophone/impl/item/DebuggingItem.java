package net.chemthunder.saxophone.impl.item;

import net.chemthunder.saxophone.impl.cca.world.WorldEventComponent;
import net.chemthunder.saxophone.impl.index.custom.SaxoWorldEvents;
import net.chemthunder.saxophone.impl.util.ModUtils;
import net.chemthunder.saxophone.impl.util.world_event.WorldEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class DebuggingItem extends Item {
    public DebuggingItem(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user.getMainHandStack().isOf(this)) {
            if (user.isSneaking()) {
                ModUtils.setWorldEvent(world, SaxoWorldEvents.NIGHT_ETERNAL);
            } else {
                user.sendMessage(Text.literal(ModUtils.getActiveEvent(world).name()), true);
            }
        } else {
            if (user.isSneaking()) {
                ModUtils.removeWorldEvent(world);
            }
        }
        return super.use(world, user, hand);
    }
}
