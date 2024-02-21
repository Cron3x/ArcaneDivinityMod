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
    public static final Block arcaneAltarBlock      = new ArcaneAltarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).strength(3).noOcclusion());
    public static final Block arcaneObeliskBlock    = new ArcaneObeliskBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).strength(3).noOcclusion());
    public static final Block pedestalBlock         = new PedestalBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).strength(1).noOcclusion());

    public static void registerBlocks(BiConsumer<Block, ResourceLocation> register){
        register.accept(arcaneAltarBlock, ZBlockNames.ARCANE_ALTAR);
        register.accept(arcaneObeliskBlock, ZBlockNames.ARCANE_OBELISK);
        register.accept(pedestalBlock, ZBlockNames.PEDESTAL);
    }

    public static void registerBlockItems(BiConsumer<Item, ResourceLocation> register){
        Item.Properties props = Services.PLATFORM.defaultItemBuilder();
        register.accept(new BlockItem(arcaneAltarBlock, props), BuiltInRegistries.BLOCK.getKey(arcaneAltarBlock));
        register.accept(new BlockItem(arcaneObeliskBlock, props), BuiltInRegistries.BLOCK.getKey(arcaneObeliskBlock));
        register.accept(new BlockItem(pedestalBlock, props), BuiltInRegistries.BLOCK.getKey(pedestalBlock));
    }
}
