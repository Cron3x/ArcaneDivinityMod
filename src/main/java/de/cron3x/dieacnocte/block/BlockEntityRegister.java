package de.cron3x.dieacnocte.block;

import de.cron3x.dieacnocte.DieAcNocteMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class BlockEntityRegister {
    public static final DeferredRegister<BlockEntityType<?>> REGISTER = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, DieAcNocteMod.MODID);

    // For some DeferredRegister<BlockEntityType<?>> REGISTER
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ArcaneAltarBlockEntity>> ARCANE_ALTAR_BLOCKENTITY = REGISTER.register(
            "arcane_altar",
            () -> BlockEntityType.Builder.of(ArcaneAltarBlockEntity::new, BlockRegister.ARCANE_ALTAR_BLOCK.get())
                    .build(null)
    );
}
