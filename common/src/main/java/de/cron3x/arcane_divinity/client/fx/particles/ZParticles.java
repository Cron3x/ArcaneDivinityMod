package de.cron3x.arcane_divinity.client.fx.particles;

import de.cron3x.arcane_divinity.Constants;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class ZParticles {
    public static final ParticleType<GlowParticleOptions> GLOW_PARTICLE = new GlowParticleType();

    public static void registerParticles(BiConsumer<ParticleType<?>, ResourceLocation> r){
        r.accept(GLOW_PARTICLE, new ResourceLocation(Constants.MOD_ID, "glow_particle"));
    }
    public static class FactoryHandler {
        public interface Consumer {
            <T extends ParticleOptions> void register(ParticleType<T> type, Function<SpriteSet, ParticleProvider<T>> constructor);
        }

        public static void registerFactories(Consumer consumer) {
            consumer.register(ZParticles.GLOW_PARTICLE, GlowParticleType.Factory::new);
        }
    }

}
