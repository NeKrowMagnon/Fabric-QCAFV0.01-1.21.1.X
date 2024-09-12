package net.nekrowmagnon.qcaf.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.nekrowmagnon.qcaf.QCAF;
import net.nekrowmagnon.qcaf.item.custom.SculkRemnantItem;

import java.util.List;

public class ModItems {

    public static final Item SCULK_REMNANT = registerItem("sculk_remnant", new SculkRemnantItem(new Item.Settings()));
    public static final Item WITHERING_SCULK_REMNANT = registerItem("withering_sculk_remnant", new Item(new Item.Settings()));
    public static final Item CALCIFYING_SCULK_REMNANT = registerItem("calcifying_sculk_remnant", new Item(new Item.Settings()) {
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("tooltip.qcaf.calcifying_sculk_remnant"));
            super.appendTooltip(stack, context, tooltip, type);
        }
    });


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(QCAF.MOD_ID, name), item);
    }
    public static void registerModItems() {
        QCAF.LOGGER.info("Registering Mod Items for " + QCAF.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(SCULK_REMNANT);
            entries.add(WITHERING_SCULK_REMNANT);
            entries.add(CALCIFYING_SCULK_REMNANT);
        });
    }
}
