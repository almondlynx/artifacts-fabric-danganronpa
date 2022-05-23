package artifacts2.mixin.mixins.client.render;

import artifacts2.init.Items;
import dev.emi.trinkets.api.TrinketsApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(PlayerModel.class)
public class PlayerModelMixin<T extends LivingEntity> extends HumanoidModel<T> {
    @Shadow @Final public ModelPart leftPants;

    @Shadow @Final public ModelPart rightPants;

    public PlayerModelMixin(float f) {
        super(f);
    }

    @Inject(method = "setupAnim", at = @At("TAIL"))
    public void setupAnim(T livingEntity, float f, float g, float h, float i, float j, CallbackInfo ci) {
        if (livingEntity instanceof AbstractClientPlayer) {
            Container trinketsInventory = TrinketsApi.getTrinketsInventory((AbstractClientPlayer)livingEntity);
            if (trinketsInventory != null && trinketsInventory.hasAnyOf(Items.LEG_LIMITING_ITEMS)) {
                if (!livingEntity.isPassenger()) {
                    leftLeg.xRot = leftLeg.xRot * 0.5f;
                    rightLeg.xRot = rightLeg.xRot * 0.5f;
                    leftPants.xRot = leftPants.xRot * 0.5f;
                    rightPants.xRot = rightPants.xRot * 0.5f;
                }
            }
        }
    }
}
