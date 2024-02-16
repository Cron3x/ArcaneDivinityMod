package de.cron3x.arcane_divinity.common.block.block_entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public abstract class AbstractBlockEntity extends BlockEntity {
    public AbstractBlockEntity(BlockEntityType<?> $$0, BlockPos $$1, BlockState $$2) {
        super($$0, $$1, $$2);
    }
    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        writePacketNBT(tag);
    }

    @NotNull
    @Override
    public final CompoundTag getUpdateTag() {
        var tag = new CompoundTag();
        writePacketNBT(tag);
        return tag;
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        readPacketNBT(tag);
    }

    public void writePacketNBT(CompoundTag cmp) {}

    public void readPacketNBT(CompoundTag cmp) {}

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
