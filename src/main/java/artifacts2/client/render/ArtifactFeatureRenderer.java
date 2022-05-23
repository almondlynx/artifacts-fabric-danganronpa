package artifacts2.client.render;

import artifacts2.item.curio.TrinketArtifactItem;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketSlots;
import dev.emi.trinkets.api.TrinketsApi;
import java.util.List;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.item.ItemStack;

/**
 * Custom trinkets feature renderer, {@link dev.emi.trinkets.TrinketFeatureRenderer} is missing parameters
 */
public class ArtifactFeatureRenderer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

	public ArtifactFeatureRenderer(
			RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> context) {
		super(context);
	}

	@Override
	public void render(PoseStack matrices, MultiBufferSource vertexConsumers, int light, AbstractClientPlayer player, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
		TrinketComponent comp = TrinketsApi.getTrinketComponent(player);
		List<String> names = TrinketSlots.getAllSlotNames();

		for (int i = 0; i < comp.getInventory().getContainerSize(); i++) {
			matrices.pushPose();
			ItemStack stack = comp.getInventory().getItem(i);
			if (stack.getItem() instanceof TrinketArtifactItem) {
				((TrinketArtifactItem) stack.getItem()).render(names.get(i), matrices, vertexConsumers, light, player, limbAngle, limbDistance, tickDelta, animationProgress, headYaw, headPitch, stack);
			}
			matrices.popPose();
		}
	}
}
