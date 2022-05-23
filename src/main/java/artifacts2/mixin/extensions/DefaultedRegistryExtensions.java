package artifacts2.mixin.extensions;

import java.util.Optional;
import net.minecraft.resources.ResourceLocation;

public interface DefaultedRegistryExtensions<T> {

	Optional<ResourceLocation> artifacts$getIdOrEmpty(T entry);
}
