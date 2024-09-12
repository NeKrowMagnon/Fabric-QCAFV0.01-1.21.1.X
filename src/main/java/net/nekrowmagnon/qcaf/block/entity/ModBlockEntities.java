package net.nekrowmagnon.qcaf.block.entity;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.nekrowmagnon.qcaf.QCAF;
import net.nekrowmagnon.qcaf.block.ModBlocks;

public class ModBlockEntities {
    public static <T extends BlockEntityType<?>> T register(String path, T blockEntityType) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of("qcaf", path), blockEntityType);
    }

    public static final BlockEntityType<WitheringSculkCatalystBlockEntity> WITHERING_SCULK_CATALYST = register(
            "withering_sculk_catalyst",
            BlockEntityType.Builder.create(WitheringSculkCatalystBlockEntity::new, ModBlocks.WITHERING_SCULK_CATALYST).build()
    );

    public static void registerBlockEntities() {
        QCAF.LOGGER.info("Registering Block Entities for " + QCAF.MOD_ID);
    }
}
