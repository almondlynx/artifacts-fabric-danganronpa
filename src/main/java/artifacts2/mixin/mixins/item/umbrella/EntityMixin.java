package artifacts2.mixin.mixins.item.umbrella;

import artifacts2.item.UmbrellaItem;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {

	@Inject(method = "isInRain", at = @At("RETURN"), cancellable = true)
	private void umbrellaBlocksRain(CallbackInfoReturnable<Boolean> info) {
		Entity self = (Entity) (Object) this;

		if (info.getReturnValueZ() && self instanceof LivingEntity && UmbrellaItem.isHeldUpInEitherHand((LivingEntity) self)) {
			info.setReturnValue(false);
		}
	}
}
