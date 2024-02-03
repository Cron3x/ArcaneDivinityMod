package de.cron3x.arcane_divinity.block;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class ModBlocks {
    public static final Block ARCANE_ALTAR_BLOCK = new ArcaneAltarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.CLAY));
}
