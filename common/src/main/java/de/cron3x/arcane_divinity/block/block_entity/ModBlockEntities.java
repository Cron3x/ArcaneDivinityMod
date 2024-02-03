package de.cron3x.arcane_divinity.block.block_entity;

import de.cron3x.arcane_divinity.Constants;
import de.cron3x.arcane_divinity.block.ModBlocks;
import de.cron3x.arcane_divinity.platform.Services;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class ModBlockEntities {
    private static final Map<ResourceLocation, BlockEntityType<?>> ALL = new HashMap<>();

    public static final BlockEntityType<ArcaneAltarBlockEntity> ARCANE_ALTAR_BLOCK_ENTITY = type(new ResourceLocation(Constants.MOD_ID,"arcane_altar"), ArcaneAltarBlockEntity::new, ModBlocks.ARCANE_ALTAR_BLOCK);

    private static <T extends BlockEntity> BlockEntityType<T> type(ResourceLocation id, BiFunction<BlockPos, BlockState, T> fn, Block... blocks){
        var res = Services.PLATFORM.createBlockEntityType(fn, blocks);
        var old = ALL.put(id, res);
        if (old != null) {
            throw new IllegalArgumentException("Duplicate id " + id);
        }
        return res;
    }
}
