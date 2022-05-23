package artifacts2.mixin.mixins.accessors;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets = "net.fabricmc.fabric.impl.tool.attribute.ToolManagerImpl$EntryImpl", remap = false)
public interface ToolManagerImplEntryImplAccessor {

	@Accessor
	int[] getTagLevels();
}
