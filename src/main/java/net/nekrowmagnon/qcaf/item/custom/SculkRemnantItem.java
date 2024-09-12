package net.nekrowmagnon.qcaf.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SculkRemnantItem extends Item {
    private static final Map<Block, Block> SCULK_REMNANT_MAP =
            Map.of(
                    Blocks.END_STONE, Blocks.SCULK
            );

    public SculkRemnantItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        Block clickedBlock = world.getBlockState(context.getBlockPos()).getBlock();

        if(SCULK_REMNANT_MAP.containsKey(clickedBlock)) {
            if(!world.isClient()) {
                world.setBlockState(context.getBlockPos(), SCULK_REMNANT_MAP.get(clickedBlock).getDefaultState());

                Objects.requireNonNull(context.getPlayer()).incrementStat(Stats.USED.getOrCreateStat(this));
                context.getStack().decrementUnlessCreative(1, context.getPlayer());

                world.playSound(null, context.getBlockPos(), SoundEvents.ENTITY_WARDEN_TENDRIL_CLICKS, SoundCategory.BLOCKS);
            }
        }



        return ActionResult.SUCCESS;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if(Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltip.qcaf.withering_sculk_remnant.shift_down"));
        } else {
            tooltip.add(Text.translatable("tooltip.qcaf.withering_sculk_remnant"));
        }

        super.appendTooltip(stack, context, tooltip, type);
    }
}
