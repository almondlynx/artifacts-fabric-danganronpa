package artifacts2.mixin.mixins.util;

import artifacts2.mixin.extensions.DefaultedRegistryExtensions;
import com.mojang.serialization.Lifecycle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.Optional;
import net.minecraft.core.DefaultedRegistry;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

@Mixin(DefaultedRegistry.class)
public abstract class DefaultedRegistryMixin<T> extends MappedRegistry<T> implements DefaultedRegistryExtensions<T> {

	public DefaultedRegistryMixin(ResourceKey<? extends Registry<T>> registryKey, Lifecycle lifecycle) {
		super(registryKey, lifecycle);
	}

	@Override
	@Unique
	public Optional<ResourceLocation> artifacts$getIdOrEmpty(T entry) {
		return Optional.ofNullable(super.getKey(entry));
	}
}
