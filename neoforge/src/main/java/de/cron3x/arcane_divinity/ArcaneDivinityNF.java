package de.cron3x.arcane_divinity;


import de.cron3x.arcane_divinity.common.block.ZBlocks;
import de.cron3x.arcane_divinity.common.block.block_entity.ZBlockEntities;
import de.cron3x.arcane_divinity.common.block.block_entity.renderers.DefaultBlockEntityRenderer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Mod(Constants.MOD_ID)
public class ArcaneDivinityNF {
    public ArcaneDivinityNF(IEventBus eventBus) {

        // This method is invoked by the NeoForge mod loader when it is ready
        // to load your mod. You can access NeoForge and Common code in this
        // project.

        // Use NeoForge to bootstrap the Common mod.
        Constants.LOG.info("Hello NeoForge world!");
        CommonClass.init();

        eventBus.addListener((RegisterEvent event) -> {
            bind(event, Registries.BLOCK, ZBlocks::registerBlocks);
            bindItems(event, ZBlocks::registerBlockItems);
            bind(event, Registries.BLOCK_ENTITY_TYPE, ZBlockEntities::registerBlockEntities);
        });
        eventBus.addListener((EntityRenderersEvent.RegisterRenderers event) -> {
            System.out.println("registerRenderers");
            event.registerBlockEntityRenderer(ZBlockEntities.ARCANE_ALTAR_BLOCK_ENTITY, DefaultBlockEntityRenderer::new);
        });
    }

    private static <E extends RegisterEvent, T> void bind(E event, ResourceKey<Registry<T>> registry, Consumer<BiConsumer<T, ResourceLocation>> source){
        if (registry.equals(event.getRegistryKey())){
            source.accept((t, rl) -> event.register(registry, rl, () -> t));
        }
    }
    private void bindItems(RegisterEvent event, Consumer<BiConsumer<Item, ResourceLocation>> source){
        if (event.getRegistryKey().equals(Registries.ITEM)){
            source.accept( (t, rl) ->{
                //TODO: Add to inv
                event.register(Registries.ITEM, rl, () -> t);
            });
        }
    }
}