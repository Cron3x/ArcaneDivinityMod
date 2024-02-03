package de.cron3x.dieacnocte.block;

import de.cron3x.dieacnocte.DieAcNocteMod;
import de.cron3x.dieacnocte.item.ItemRegister;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockRegister {
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(DieAcNocteMod.MODID);

    // Creates a new Block with the id "examplemod:example_block", combining the namespace and path
    public static final DeferredBlock<Block> ARCANE_ALTAR_BLOCK = BLOCKS.registerSimpleBlock("arcane_altar", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    // Creates a new BlockItem with the id "examplemod:example_block", combining the namespace and path
    public static final DeferredItem<BlockItem> ARCANE_ALTAR_BLOCK_ITEM = ItemRegister.ITEMS.registerSimpleBlockItem("arcane_altar", ARCANE_ALTAR_BLOCK);
}
