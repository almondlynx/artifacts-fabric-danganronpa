package artifacts2.mixin.mixins.item.umbrella.client;

import artifacts2.Artifacts;
import artifacts2.init.Items;
import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

// Priority is higher so we can inject into canvas' renderItem overwrite
// TODO: rewrite this using FabricBakedModel if/when RenderContext gets the transform mode
@Mixin(value = ItemRenderer.class, priority = 1500)
public abstract class ItemRendererMixin {

	@Shadow @Final private ItemModelShaper itemModelShaper;
	@Unique
	private static final ModelResourceLocation UMBRELLA_HELD_MODEL = new ModelResourceLocation(Artifacts.id("umbrella_in_hand"), "inventory");
	@Unique
	private static final ModelResourceLocation UMBRELLA_ICON_MODEL = new ModelResourceLocation(Artifacts.id("umbrella"), "inventory");

	@ModifyVariable(method = "getModel", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/client/renderer/ItemModelShaper;getItemModel(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/client/resources/model/BakedModel;"))
	private BakedModel setUmbrellaHeldModel(BakedModel model, ItemStack stack) {
		return stack.getItem() == Items.UMBRELLA ? this.itemModelShaper.getModelManager().getModel(UMBRELLA_HELD_MODEL) : model;
	}

	@ModifyVariable(method = "render", argsOnly = true, at = @At(value = "HEAD"))
	private BakedModel setUmbrellaIconModel(BakedModel model, ItemStack stack, ItemTransforms.TransformType renderMode) {
		boolean shouldUseIcon = renderMode == ItemTransforms.TransformType.GUI ||
								renderMode == ItemTransforms.TransformType.GROUND ||
								renderMode == ItemTransforms.TransformType.FIXED;

		return stack.getItem() == Items.UMBRELLA && shouldUseIcon
				? this.itemModelShaper.getModelManager().getModel(UMBRELLA_ICON_MODEL) : model;
	}
}
