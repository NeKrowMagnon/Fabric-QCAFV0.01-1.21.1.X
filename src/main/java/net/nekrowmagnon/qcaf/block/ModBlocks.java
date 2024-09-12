package net.nekrowmagnon.qcaf.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.nekrowmagnon.qcaf.QCAF;
import net.nekrowmagnon.qcaf.block.custom.WitheringSculkCatalystBlock;

public class ModBlocks {

    public static final Block SHULK_POLYP = registerBlock("shulk_polyp",
            new Block(AbstractBlock.Settings.create().strength(2f)
                    .sounds(BlockSoundGroup.FROGLIGHT)));
    public static final Block SHULK_CALYX = registerBlock("shulk_calyx",
            new Block(AbstractBlock.Settings.create().strength(10f).sounds(BlockSoundGroup.CALCITE).requiresTool()));
    public static final Block WITHERING_SCULK = registerBlock("withering_sculk",
            new Block(AbstractBlock.Settings.create().strength(0.75f)
                    .sounds(BlockSoundGroup.SCULK)));
    public static final Block WITHERING_SCULK_CATALYST = registerBlock("withering_sculk_catalyst",
            new WitheringSculkCatalystBlock(AbstractBlock.Settings.create().strength(1.5f).requiresTool()
                    .sounds(BlockSoundGroup.SCULK_CATALYST)));




    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(QCAF.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(QCAF.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        QCAF.LOGGER.info("Registering Mod Blocks for " + QCAF.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.add(ModBlocks.SHULK_POLYP);
            entries.add(ModBlocks.SHULK_CALYX);
        });
    }
}
