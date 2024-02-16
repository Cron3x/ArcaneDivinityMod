package de.cron3x.arcane_divinity.common.block;

import de.cron3x.arcane_divinity.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.function.BiConsumer;

public final class ZBlocks {
    public static final Block arcaneAltarBlock = new ArcaneAltarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).strength(3));

    public static void registerBlocks(BiConsumer<Block, ResourceLocation> register){
        register.accept(arcaneAltarBlock, ZBlockNames.ARCANE_ALTAR);
    }

    public static void registerBlockItems(BiConsumer<Item, ResourceLocation> register){
        Item.Properties props = Services.PLATFORM.defaultItemBuilder();
        register.accept(new BlockItem(arcaneAltarBlock, props), BuiltInRegistries.BLOCK.getKey(arcaneAltarBlock));
    }
}
