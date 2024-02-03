package de.cron3x.arcane_divinity.block;

import de.cron3x.arcane_divinity.Constants;
import de.cron3x.arcane_divinity.block.block_entity.ArcaneAltarBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockEntityRegister {
    public static final DeferredRegister<BlockEntityType<?>> REGISTER = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Constants.MOD_ID);

    // For some DeferredRegister<BlockEntityType<?>> REGISTER
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ArcaneAltarBlockEntity>> ARCANE_ALTAR_BLOCKENTITY = REGISTER.register(
            "arcane_altar",
            () -> BlockEntityType.Builder.of(ArcaneAltarBlockEntity::new, BlockRegister.ARCANE_ALTAR_BLOCK.get())
                    .build(null)
    );
}