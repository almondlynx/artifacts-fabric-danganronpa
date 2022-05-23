package artifacts2.client.render.trinket.renderer;

import artifacts2.Artifacts;
import artifacts2.client.render.TrinketRenderHelper;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class SimpleCurioRenderer implements CurioRenderer {

    private final ResourceLocation texture;
    private final HumanoidModel<LivingEntity> model;

    public SimpleCurioRenderer(String texturePath, HumanoidModel<LivingEntity> model) {
        this(Artifacts.id(String.format("textures/entity/curio/%s.png", texturePath)), model);
    }

    public SimpleCurioRenderer(ResourceLocation texture, HumanoidModel<LivingEntity> model) {
        this.texture = texture;
        this.model = model;
    }

    protected ResourceLocation getTexture() {
        return texture;
    }

    protected HumanoidModel<LivingEntity> getModel() {
        return model;
    }

    @Override
    public final void render(String slot, int index, PoseStack matrixStack, MultiBufferSource buffer, int light, LivingEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ticks, float headYaw, float headPitch, ItemStack stack) {
        HumanoidModel<LivingEntity> model = getModel();

        model.setupAnim(entity, limbSwing, limbSwingAmount, ticks, headYaw, headPitch);
        model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
        TrinketRenderHelper.followBodyRotations(entity, model);
        render(matrixStack, buffer, light, stack.hasFoil());
    }

    protected void render(PoseStack matrixStack, MultiBufferSource buffer, int light, boolean hasFoil) {
        RenderType renderType = model.renderType(getTexture());
        VertexConsumer vertexBuilder = ItemRenderer.getFoilBuffer(buffer, renderType, false, hasFoil);
        model.renderToBuffer(matrixStack, vertexBuilder, light, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
    }
}
