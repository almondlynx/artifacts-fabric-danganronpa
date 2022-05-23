package artifacts2.mixin.mixins.item.umbrella.client;

import artifacts2.item.UmbrellaItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidModel.class)
public abstract class HumanoidModelMixin<T extends LivingEntity> {

	@Shadow
	public ModelPart rightArm;
	@Shadow
	public ModelPart leftArm;

	// Target is unresolved because method owner is a generic T
	// Seems to work fine, but has failed to apply once or twice in dev (in a fresh runtime)
	@SuppressWarnings("UnresolvedMixinReference")
	@Inject(method = "setupAnim", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getMainArm()Lnet/minecraft/world/entity/HumanoidArm;"))
	private void reduceHandSwing(T entity, float f, float g, float h, float i, float j, CallbackInfo info) {
		boolean heldMainHand = UmbrellaItem.getHeldStatusForHand(entity, InteractionHand.MAIN_HAND) == UmbrellaItem.HeldStatus.HELD_UP;
		boolean heldOffHand = UmbrellaItem.getHeldStatusForHand(entity, InteractionHand.OFF_HAND) == UmbrellaItem.HeldStatus.HELD_UP;
		boolean rightHanded = Minecraft.getInstance().options.mainHand == HumanoidArm.RIGHT;

		if ((heldMainHand && rightHanded) || (heldOffHand && !rightHanded)) {
			this.rightArm.xRot /= 8;
		}

		if ((heldMainHand && !rightHanded) || (heldOffHand && rightHanded)) {
			this.leftArm.xRot /= 8;
		}
	}
}
