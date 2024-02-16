package de.cron3x.arcane_divinity.common.block.block_entity.renderers;

import de.cron3x.arcane_divinity.common.block.block_entity.ArcaneAltarBlockEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ArcaneAltarBlockEntityRenderer extends GeoBlockRenderer<ArcaneAltarBlockEntity> {
    public ArcaneAltarBlockEntityRenderer(EntityRendererProvider.Context context) {
        // Docs are wrong? --- https://github.com/bernie-g/geckolib/wiki/Geckolib-Blocks-(Geckolib4)
        super(ArcaneAltarBlockEntityModel());
    }
}
