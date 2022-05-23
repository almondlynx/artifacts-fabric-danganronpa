package artifacts2.mixin.mixins.item.umbrella.client;

import artifacts2.item.UmbrellaItem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin<T extends LivingEntity, M extends EntityModel<T>> extends EntityRenderer<T> {

	@Shadow
	protected M model;

	protected LivingEntityRendererMixin(EntityRenderDispatcher dispatcher) {
		super(dispatcher);
	}

	@Inject(method = "render", at = @At("HEAD"))
	private void renderUmbrella(T entity, float f, float g, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i, CallbackInfo info) {
		if (this.model instanceof HumanoidModel) {
			//noinspection rawtypes
			HumanoidModel model = (HumanoidModel) this.model;

			boolean heldMainHand = UmbrellaItem.getHeldStatusForHand(entity, InteractionHand.MAIN_HAND) == UmbrellaItem.HeldStatus.HELD_UP;
			boolean heldOffHand = UmbrellaItem.getHeldStatusForHand(entity, InteractionHand.OFF_HAND) == UmbrellaItem.HeldStatus.HELD_UP;
			boolean rightHanded = Minecraft.getInstance().options.mainHand == HumanoidArm.RIGHT;

			if ((heldMainHand && rightHanded) || (heldOffHand && !rightHanded)) {
				model.rightArmPose = HumanoidModel.ArmPose.THROW_SPEAR;
			}

			if ((heldMainHand && !rightHanded) || (heldOffHand && rightHanded)) {
				model.leftArmPose = HumanoidModel.ArmPose.THROW_SPEAR;
			}
		}
	}
}
