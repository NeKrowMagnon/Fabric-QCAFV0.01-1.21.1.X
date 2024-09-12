package net.nekrowmagnon.qcaf.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.nekrowmagnon.qcaf.QCAF;
import net.nekrowmagnon.qcaf.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup REFORMED_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
                    Identifier.of(QCAF.MOD_ID, "reformed_blocks"),
                    FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.SHULK_POLYP))
                            .displayName(Text.translatable("itemgroup.qcaf.reformed_blocks"))
                            .entries((displayContext, entries) -> {
                                entries.add(ModBlocks.SHULK_POLYP);
                                entries.add(ModBlocks.SHULK_CALYX);
                                entries.add(ModBlocks.WITHERING_SCULK);
                                entries.add(ModBlocks.WITHERING_SCULK_CATALYST);

                            }).build());

    public static final ItemGroup REFORMED_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(QCAF.MOD_ID, "reformed_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.SCULK_REMNANT))
                    .displayName(Text.translatable("itemgroup.qcaf.reformed_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.SCULK_REMNANT);
                        entries.add(ModItems.WITHERING_SCULK_REMNANT);
                        entries.add(ModItems.CALCIFYING_SCULK_REMNANT);

                    }).build());



    public static void registerItemGroups() {
        QCAF.LOGGER.info("Registering Item Groups for " + QCAF.MOD_ID);
    }
}
