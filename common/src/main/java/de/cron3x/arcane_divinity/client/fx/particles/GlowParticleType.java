package de.cron3x.arcane_divinity.client.fx.particles;

import com.mojang.serialization.Codec;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.ParticleType;

public class GlowParticleType extends ParticleType<GlowParticleOptions> {
    protected GlowParticleType() {
        super(false, GlowParticleOptions.DESERIALIZER);
    }

    @Override
    public Codec<GlowParticleOptions> codec() {
        return GlowParticleOptions.CODEC;
    }

    public static class Factory implements ParticleProvider<GlowParticleOptions> {
        private final SpriteSet sprite;

        public Factory(SpriteSet sprite) {
            this.sprite = sprite;
        }

        @Override
        public Particle createParticle(GlowParticleOptions data, ClientLevel world, double x, double y, double z, double mx, double my, double mz) {
            FXGlow ret = new FXGlow(world, x, y, z, mx, my, mz, data.size, data.r, data.g, data.b, data.a);
            ret.pickSprite(sprite);
            return ret;
        }
    }

}
