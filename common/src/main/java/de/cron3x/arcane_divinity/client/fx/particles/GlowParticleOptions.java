package de.cron3x.arcane_divinity.client.fx.particles;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class GlowParticleOptions implements ParticleOptions {
    public static final Codec<GlowParticleOptions> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                    Codec.FLOAT.fieldOf("size").forGetter(d -> d.size),
                    Codec.FLOAT.fieldOf("r").forGetter(d -> d.r),
                    Codec.FLOAT.fieldOf("g").forGetter(d -> d.g),
                    Codec.FLOAT.fieldOf("b").forGetter(d -> d.b),
                    Codec.FLOAT.fieldOf("maxAgeMul").forGetter(d -> d.a)
            )
            .apply(instance, GlowParticleOptions::new));

    public final float size;
    public final float r,g,b,a;

    public GlowParticleOptions(float size, float r, float g, float b, float a) {
        this.size = size;
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    @Override
    public ParticleType<?> getType() {
        return ZParticles.GLOW_PARTICLE;
    }

    @Override
    public void writeToNetwork(FriendlyByteBuf buf) {
        buf.writeFloat(size);
        buf.writeFloat(r);
        buf.writeFloat(g);
        buf.writeFloat(b);
        buf.writeFloat(a);
    }

    @NotNull
    @Override
    public String writeToString() {
        return String.format(Locale.ROOT, "%s %.2f %.2f %.2f %.2f %.2f",
                BuiltInRegistries.PARTICLE_TYPE.getKey(getType()), this.size, this.r, this.g, this.b, this.a);
    }

    public static final Deserializer<GlowParticleOptions> DESERIALIZER = new Deserializer<>() {
        @NotNull
        @Override
        public GlowParticleOptions fromCommand(@NotNull ParticleType<GlowParticleOptions> type, @NotNull StringReader reader) throws CommandSyntaxException {
            reader.expect(' ');
            float size = reader.readFloat();
            reader.expect(' ');
            float r = reader.readFloat();
            reader.expect(' ');
            float g = reader.readFloat();
            reader.expect(' ');
            float b = reader.readFloat();
            reader.expect(' ');
            int a = reader.readInt();
            reader.expect(' ');
            boolean fake = reader.readBoolean();
            reader.expect(' ');
            boolean corrupt = reader.readBoolean();

            return new GlowParticleOptions(size, r, g, b, a);
        }

        @Override
        public GlowParticleOptions fromNetwork(@NotNull ParticleType<GlowParticleOptions> type, FriendlyByteBuf buf) {
            return new GlowParticleOptions(buf.readFloat(), buf.readFloat(), buf.readFloat(), buf.readFloat(), buf.readFloat());
        }
    };


}
