package artifacts.mixin.item.flippers.client;

import artifacts.extensions.LivingEntityExtensions;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin implements LivingEntityExtensions {

	@ModifyArg(method = "goDownInWater", index = 1, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/phys/Vec3;add(DDD)Lnet/minecraft/world/phys/Vec3;"))
	private double increaseSwimSneakSpeed(double y) {
		return artifacts$getIncreasedSwimSpeed(y);
	}
}
