package de.cron3x.arcane_divinity.block;

import de.cron3x.arcane_divinity.Constants;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockRegister {
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Constants.MOD_ID);

    // Creates a new Block with the id "examplemod:example_block", combining the namespace and path
    public static final DeferredBlock<Block> ARCANE_ALTAR_BLOCK = BLOCKS.register("arcane_altar", () -> ModBlocks.ARCANE_ALTAR_BLOCK);
    // Creates a new BlockItem with the id "examplemod:example_block", combining the namespace and path
    // public static final DeferredItem<BlockItem> ARCANE_ALTAR_BLOCK_ITEM = ItemRegister.ITEMS.registerSimpleBlockItem("arcane_altar", ARCANE_ALTAR_BLOCK);
}
