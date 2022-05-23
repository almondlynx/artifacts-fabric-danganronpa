package artifacts2.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;

/**
 * Some helper methods for rendering trinket.
 * This code is from <a href="https://github.com/TheIllusiveC4/Curios/blob/1.16.x-fabric/src/main/java/top/theillusivec4/curios/api/type/component/IRenderableCurio.java#L78">Curios api</a>
 */
public final class TrinketRenderHelper {

	/**
	 * Rotates the rendering for the models based on the entity's poses and movements. This will do
	 * nothing if the entity render object does not implement {@link LivingEntityRenderer} or if the
	 * model does not implement {@link HumanoidModel}).
	 *
	 * @param livingEntity The wearer of the trinket
	 * @param model       The model to align to the body movement
	 */
	public static void followBodyRotations(final LivingEntity livingEntity,
										   final HumanoidModel<LivingEntity> model) {

		EntityRenderer<? super LivingEntity> render = Minecraft.getInstance()
				.getEntityRenderDispatcher().getRenderer(livingEntity);

		if (render instanceof LivingEntityRenderer) {
			//noinspection unchecked
			LivingEntityRenderer<LivingEntity, EntityModel<LivingEntity>> livingRenderer =
					(LivingEntityRenderer<LivingEntity, EntityModel<LivingEntity>>) render;
			EntityModel<LivingEntity> entityModel = livingRenderer.getModel();

			if (entityModel instanceof HumanoidModel) {
				HumanoidModel<LivingEntity> bipedModel = (HumanoidModel<LivingEntity>) entityModel;
				bipedModel.copyPropertiesTo(model);
			}
		}
	}
}
