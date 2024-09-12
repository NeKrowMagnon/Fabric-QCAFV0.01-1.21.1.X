package net.nekrowmagnon.qcaf.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.SculkCatalystBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.nekrowmagnon.qcaf.block.entity.ModBlockEntities;
import net.nekrowmagnon.qcaf.block.entity.WitheringSculkCatalystBlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WitheringSculkCatalystBlock extends BlockWithEntity implements BlockEntityProvider {
    public static final MapCodec<WitheringSculkCatalystBlock> CODEC = createCodec(WitheringSculkCatalystBlock::new);
    public static final BooleanProperty BLOOM = Properties.BLOOM;
    private final IntProvider experience = ConstantIntProvider.create(5);


    @Override
    public MapCodec<WitheringSculkCatalystBlock> getCodec() {
        return CODEC;
    }

    public WitheringSculkCatalystBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(BLOOM, Boolean.FALSE));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(BLOOM);
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if ((Boolean)state.get(BLOOM)) {
            world.setBlockState(pos, state.with(BLOOM, Boolean.FALSE), Block.NOTIFY_ALL);
        }
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new WitheringSculkCatalystBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return world.isClient ? null : validateTicker(type, ModBlockEntities.WITHERING_SCULK_CATALYST, WitheringSculkCatalystBlockEntity::tick);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack tool, boolean dropExperience) {
        super.onStacksDropped(state, world, pos, tool, dropExperience);
        if (dropExperience) {
            this.dropExperienceWhenMined(world, pos, tool, this.experience);
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        tooltip.add(Text.translatable("tooltip.qcaf.withering_sculk_catalyst.tooltip"));
        super.appendTooltip(stack, context, tooltip, options);
    }
}
