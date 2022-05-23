package artifacts2.client.render.trinket.renderer;

import artifacts2.Artifacts;
import artifacts2.client.render.RenderTypes;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class GlowingCurioRenderer extends SimpleCurioRenderer {

    private final ResourceLocation glowTexture;

    public GlowingCurioRenderer(String name, HumanoidModel<LivingEntity> model) {
        super(String.format("%s/%s", name, name), model);
        this.glowTexture = Artifacts.id(String.format("textures/entity/curio/%s/%s_glow.png", name, name));
    }

    private ResourceLocation getGlowTexture() {
        return glowTexture;
    }

    @Override
    protected void render(PoseStack matrixStack, MultiBufferSource buffer, int light, boolean hasFoil) {
        super.render(matrixStack, buffer, light, hasFoil);
        RenderType renderType = RenderTypes.unlit(getGlowTexture());
        VertexConsumer builder = ItemRenderer.getFoilBuffer(buffer, renderType, false, hasFoil);
        getModel().renderToBuffer(matrixStack, builder, LightTexture.pack(15, 15), OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
    }
}
