package de.cron3x.arcane_divinity.common.block.block_entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

public class ArcaneAltarBlockEntity extends SimpleInventoryBlockEntity implements GeoBlockEntity {

    protected static final RawAnimation ACTIVATE = RawAnimation.begin().thenPlay("animation.arcane_altar.misc.activate");
    protected static final RawAnimation DEACTIVATE = RawAnimation.begin().thenPlay("animation.arcane_altar.misc.deactivate");
    protected static final RawAnimation ACTIVE_IDLE = RawAnimation.begin().thenLoop("animation.arcane_altar.misc.active_idle");

    private final AnimatableInstanceCache animationCache = GeckoLibUtil.createInstanceCache(this);

    private int ticks = 0;
    private boolean shouldBeActive = false;
    private boolean isActive = false;

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

    public static void clientTick(Level level, BlockPos blockPos, BlockState blockState, ArcaneAltarBlockEntity self) {}
    public static void serverTick(Level level, BlockPos blockPos, BlockState blockState, ArcaneAltarBlockEntity self) {
        ++self.ticks;

        if (self.shouldBeActive && !self.isActive){
            self.particleAnimationActivation((ServerLevel) level, blockPos);
        } else if (!self.shouldBeActive && self.isActive) {
            //TODO: Deactivation Animation
            System.out.println("//TODO: Deactivation Animation");
        }
    }

    private void particleAnimationActivation(ServerLevel level, BlockPos blockPos){
        double radius = 1.25;

        if (ticks <= 100){
            double x = radius * Math.cos(ticks);
            double z = radius * Math.sin(ticks);

            double fx = radius * Math.cos(ticks+1);
            double fz = radius * Math.sin(ticks+1);

            level.sendParticles(ParticleTypes.WAX_OFF, blockPos.getX()+0.5+x, blockPos.getY() + 1, blockPos.getZ()+0.5+z, 1, 0, 0, 0, 0);
            level.sendParticles(ParticleTypes.ENCHANT, blockPos.getX()+0.5+fx, blockPos.getY() + 1, blockPos.getZ()+0.5+fz, 1, 0, 0, 0, 20);
        }
        if (ticks >= 100 && ticks <= 230){
            animateToPillar(level, blockPos.getCenter(), blockPos.offset( 3, 3, 3).getCenter());
            animateToPillar(level, blockPos.getCenter(), blockPos.offset(-3, 3, 3).getCenter());
            animateToPillar(level, blockPos.getCenter(), blockPos.offset( 3, 3,-3).getCenter());
            animateToPillar(level, blockPos.getCenter(), blockPos.offset(-3, 3,-3).getCenter());
        }
        if (ticks > 230){
            level.sendParticles(ParticleTypes.FLASH, blockPos.getX()+.5, blockPos.getY()+1.5,blockPos.getZ()+.5, 1,0,0,0,0);
            ticks = 0;
            isActive = true;
            shouldBeActive = true;
            level.sendBlockUpdated(blockPos, this.getBlockState(), this.getBlockState(), 2);
            setChanged();
        }
    }

    private void animateToPillar(ServerLevel level, Vec3 altarPos, Vec3 pillarPos){

        double dx = pillarPos.x() - (altarPos.x() + .5);
        double dy = pillarPos.y() - (altarPos.y() + .5);
        double dz = pillarPos.z() - (altarPos.z() + .5);

        double px = (dx * (ticks-100)) / 100;
        double py = (dy * (ticks-100)) / 100;
        double pz = (dz * (ticks-100)) / 100;

        System.out.println("dx = " + dx);
        System.out.println("px: " + px + " | py: " + py + " | pz: " + pz);

        level.sendParticles(ParticleTypes.ELECTRIC_SPARK, altarPos.x()+px, altarPos.y()+py, altarPos.z()+pz, 1, 0, 0, 0, 1);
    }

    public void setShouldBeActive(boolean shouldBeActive) {
        this.shouldBeActive = shouldBeActive;
        this.isActive = false;
        this.ticks = 0;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, state -> {
            System.out.println("registerControllers:: shouldBeActive = " +shouldBeActive+ " | isActive = "+isActive);
            if (this.shouldBeActive && !this.isActive) {
                return state.setAndContinue(ACTIVATE);
            } else if (this.shouldBeActive && this.isActive) {
                return state.setAndContinue(ACTIVE_IDLE);
            } else {
                return state.setAndContinue(DEACTIVATE);
            }
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.animationCache;
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        this.isActive = tag.getBoolean("is_active");
        this.shouldBeActive = tag.getBoolean("should_be_active");
        super.load(tag);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putBoolean("is_active", this.isActive);
        tag.putBoolean("should_be_active", this.shouldBeActive);
    }
}
