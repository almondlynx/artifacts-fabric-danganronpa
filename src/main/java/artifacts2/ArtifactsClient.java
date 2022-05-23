package artifacts2;

import artifacts2.client.render.ArtifactFeatureRenderer;
import artifacts2.client.render.trinket.CurioRenderers;
import artifacts2.init.Items;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class ArtifactsClient implements ClientModInitializer {

	private static final ModelResourceLocation UMBRELLA_HELD_MODEL = new ModelResourceLocation(Artifacts.id("umbrella_in_hand"), "inventory");

	@Override
	public void onInitializeClient() {
		CurioRenderers.setupCurioRenderers();

		// Held Umbrella model
		ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> out.accept(UMBRELLA_HELD_MODEL));

		// ModelPredicateProvider for rendering of umbrella blocking
		FabricModelPredicateProviderRegistry.register(Items.UMBRELLA, new ResourceLocation("blocking"), (stack, world, entity)
				-> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1 : 0);

		LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper) -> {
			if (entityRenderer instanceof PlayerRenderer) {
				registrationHelper.register(new ArtifactFeatureRenderer((PlayerRenderer) entityRenderer));
			}
		});
	}
}
