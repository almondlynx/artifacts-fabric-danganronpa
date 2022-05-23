package artifacts2.mixin.mixins.client.render;

import artifacts2.Artifacts;
import artifacts2.client.render.trinket.CurioRenderers;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerRenderer.class)
public abstract class PlayerRendererMixin {

	@Inject(method = "renderLeftHand", at = @At("TAIL"))
	private void renderLeftGlove(PoseStack matrixStack, MultiBufferSource buffer, int light, AbstractClientPlayer player, CallbackInfo callbackInfo) {
		renderArm(matrixStack, buffer, light, player, HumanoidArm.LEFT);
	}

	@Inject(method = "renderRightHand", at = @At("TAIL"))
	private void renderRightGlove(PoseStack matrixStack, MultiBufferSource buffer, int light, AbstractClientPlayer player, CallbackInfo callbackInfo) {
		renderArm(matrixStack, buffer, light, player, HumanoidArm.RIGHT);
	}

	@Unique
	private static void renderArm(PoseStack matrixStack, MultiBufferSource buffer, int light, AbstractClientPlayer player, HumanoidArm handSide) {
		if (!Artifacts.CONFIG.general.showFirstPersonGloves) {
			return;
		}

		InteractionHand hand = handSide == player.getMainArm() ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
		String slotGroup = hand == InteractionHand.MAIN_HAND ? SlotGroups.HAND : SlotGroups.OFFHAND;
		ItemStack stack = TrinketsApi.getTrinketComponent(player).getStack(slotGroup, Slots.GLOVES);

		CurioRenderers.getGloveRenderer(stack).ifPresent(renderer ->
				renderer.renderFirstPersonArm(matrixStack, buffer, light, player, handSide, stack.hasFoil()));
	}
}
