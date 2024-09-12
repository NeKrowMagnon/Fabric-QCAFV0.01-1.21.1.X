package net.nekrowmagnon.qcaf;

import net.fabricmc.api.ModInitializer;

import net.nekrowmagnon.qcaf.block.ModBlocks;
import net.nekrowmagnon.qcaf.block.entity.ModBlockEntities;
import net.nekrowmagnon.qcaf.item.ModItemGroups;
import net.nekrowmagnon.qcaf.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QCAF implements ModInitializer {
	public static final String MOD_ID = "qcaf";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModBlockEntities.registerBlockEntities();
	}
}