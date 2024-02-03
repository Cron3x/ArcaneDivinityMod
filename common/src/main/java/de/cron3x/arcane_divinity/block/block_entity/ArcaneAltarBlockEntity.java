package de.cron3x.arcane_divinity.block.block_entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class ArcaneAltarBlockEntity extends BlockEntity {
    boolean activated = false;
    boolean shouldActivate = false;
    private int ticks = 0;

    public ArcaneAltarBlockEntity(BlockPos $$1, BlockState $$2) {
        super(ModBlockEntities.ARCANE_ALTAR_BLOCK_ENTITY, $$1, $$2);
    }

    public static void commonTick(Level level, BlockPos pos, BlockState state, ArcaneAltarBlockEntity self){
        ++self.ticks;
        if (self.activated) {

        }
        else if (self.shouldActivate){

        }
        else {

        }
    }
}
