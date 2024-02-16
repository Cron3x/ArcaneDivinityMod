package de.cron3x.arcane_divinity.common.block.block_entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class ArcaneAltarBlockEntity extends SimpleInventoryBlockEntity {

    private int ticks = 0;
    private boolean shouldActivate = false;

    public ArcaneAltarBlockEntity(BlockPos pos, BlockState state) {
        super(ZBlockEntities.ARCANE_ALTAR_BLOCK_ENTITY, pos, state);
    }

    @Override
    protected SimpleContainer createItemHandler() {
        return new SimpleContainer(2) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
        };
    }

    public static void clientTick(Level level, BlockPos blockPos, BlockState blockState, ArcaneAltarBlockEntity self) {
    }
    public static void serverTick(Level level, BlockPos blockPos, BlockState blockState, ArcaneAltarBlockEntity self) {
        ++self.ticks;

        if (self.shouldActivate){
            self.particleAnimationActivation((ServerLevel) level, blockPos);
        }
    }

    private void particleAnimationActivation(ServerLevel level, BlockPos blockPos){
        double radius = 1.25;

        if (ticks <= 160){
            animateToPillar(level, blockPos.getCenter(), blockPos.getCenter().add(2,1.5,2));
            animateToPillar(level, blockPos.getCenter(), blockPos.getCenter().add(-2,1.5,2));
            animateToPillar(level, blockPos.getCenter(), blockPos.getCenter().add(2,1.5,-2));
            animateToPillar(level, blockPos.getCenter(), blockPos.getCenter().add(-2,1.5,-2));
        }
        if (ticks > 155 && ticks < 220){
            double x = radius * Math.cos(ticks);
            double z = radius * Math.sin(ticks);

            double xb = radius * Math.cos(ticks+1);
            double zb = radius * Math.sin(ticks+1);

            level.sendParticles(ParticleTypes.WAX_OFF, blockPos.getX()+0.5+x, blockPos.getY() + 1.5, blockPos.getZ()+0.5+z, 1, 0, 0, 0, 0);
            level.sendParticles(ParticleTypes.ENCHANT, blockPos.getX()+0.5+xb, blockPos.getY() + 1.5, blockPos.getZ()+0.5+zb, 1, 0, 0, 0, 20);
        }
        if (ticks > 230){
            level.sendParticles(ParticleTypes.FLASH, blockPos.getX()+.5, blockPos.getY()+1.5,blockPos.getZ()+.5, 1,0,0,0,0);
            ticks = 0;
            shouldActivate = false;
        }
    }

    private void animateToPillar(ServerLevel level, Vec3 altarPos, Vec3 pillarPos){
        int cur = ticks - 60;

        double dx = pillarPos.x - altarPos.x;
        double dy = pillarPos.y - altarPos.y - 1;
        double dz = pillarPos.z - altarPos.z;

        double px = (100 * dx) / cur;
        double py = (100 * dy) / cur;
        double pz = (100 * dz) / cur;

        System.out.println("dx = " + dx);
        System.out.println("px: " + px + " | py: " + py + " | pz: " + pz);

        level.sendParticles(ParticleTypes.ELECTRIC_SPARK, altarPos.x+px, altarPos.y+py, altarPos.z+pz, 1, 0, 0, 0, 1);
    }

    public void setShouldActivate(boolean shouldActivate) {
        this.shouldActivate = shouldActivate;
    }
    public boolean isShouldActivate() {
        return shouldActivate;
    }
}
