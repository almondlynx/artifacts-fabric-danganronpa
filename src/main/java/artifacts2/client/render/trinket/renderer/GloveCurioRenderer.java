package artifacts2.client.render.trinket.renderer;

import artifacts2.Artifacts;
import artifacts2.client.render.TrinketRenderHelper;
import artifacts2.client.render.trinket.model.HandsModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.emi.trinkets.api.SlotGroups;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.function.Function;

public class GloveCurioRenderer implements CurioRenderer {

    private final ResourceLocation defaultTexture;
    private final ResourceLocation slimTexture;
    private final HandsModel defaultModel;
    private final HandsModel slimModel;

    public GloveCurioRenderer(String name) {
        this(String.format("glove/%s/%s_default", name, name), String.format("glove/%s/%s_slim", name, name), HandsModel::glove);
    }

    public GloveCurioRenderer(String name, Function<Boolean, HandsModel> modelFactory) {
        this(String.format("%s/%s_default", name, name), String.format("%s/%s_slim", name, name), modelFactory);
    }

    public GloveCurioRenderer(String defaultTexturePath, String slimTexturePath, Function<Boolean, HandsModel> modelFactory) {
        this.defaultTexture = Artifacts.id(String.format("textures/entity/curio/%s.png", defaultTexturePath));
        this.slimTexture = Artifacts.id(String.format("textures/entity/curio/%s.png", slimTexturePath));
        this.defaultModel = modelFactory.apply(false);
        this.slimModel = modelFactory.apply(true);
    }

    protected ResourceLocation getTexture(boolean hasSlimArms) {
        return hasSlimArms ? slimTexture : defaultTexture;
    }

    protected HandsModel getModel(boolean hasSlimArms) {
        return hasSlimArms ? slimModel : defaultModel;
    }

    protected static boolean hasSlimArms(Entity entity) {
        return entity instanceof AbstractClientPlayer && ((AbstractClientPlayer) entity).getModelName().equals("slim");
    }

    @Override
    public final void render(String slot, int index, PoseStack matrixStack, MultiBufferSource buffer, int light, LivingEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ticks, float headYaw, float headPitch, ItemStack stack) {
        boolean hasSlimArms = hasSlimArms(entity);
        HandsModel model = getModel(hasSlimArms);
        HumanoidArm handSide = slot.split(":")[0].equals(SlotGroups.HAND) ? entity.getMainArm() : entity.getMainArm().getOpposite();

        model.setupAnim(entity, limbSwing, limbSwingAmount, ticks, headYaw, headPitch);
        model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
        TrinketRenderHelper.followBodyRotations(entity, model);

        renderArm(model, matrixStack, buffer, handSide, light, hasSlimArms, stack.hasFoil());
    }

    protected void renderArm(HandsModel model, PoseStack matrixStack, MultiBufferSource buffer, HumanoidArm handSide, int light, boolean hasSlimArms, boolean hasFoil) {
        RenderType renderType = model.renderType(getTexture(hasSlimArms));
        VertexConsumer vertexBuilder = ItemRenderer.getFoilBuffer(buffer, renderType, false, hasFoil);
        model.renderHand(handSide, matrixStack, vertexBuilder, light, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
    }

    public final void renderFirstPersonArm(PoseStack matrixStack, MultiBufferSource buffer, int light, AbstractClientPlayer player, HumanoidArm side, boolean hasFoil) {
        if (!player.isSpectator()) {
            boolean hasSlimArms = hasSlimArms(player);
            HandsModel model = getModel(hasSlimArms);

            ModelPart arm = side == HumanoidArm.LEFT ? model.leftArm : model.rightArm;

            model.setAllVisible(false);
            arm.visible = true;

            model.crouching = false;
            model.attackTime = model.swimAmount = 0;
            model.setupAnim(player, 0, 0, 0, 0, 0);
            arm.xRot = 0;

            renderFirstPersonArm(model, arm, matrixStack, buffer, light, hasSlimArms, hasFoil);
        }
    }

    protected void renderFirstPersonArm(HandsModel model, ModelPart arm, PoseStack matrixStack, MultiBufferSource buffer, int light, boolean hasSlimArms, boolean hasFoil) {
        RenderType renderType = model.renderType(getTexture(hasSlimArms));
        VertexConsumer builder = ItemRenderer.getFoilBuffer(buffer, renderType, false, hasFoil);
        arm.render(matrixStack, builder, light, OverlayTexture.NO_OVERLAY);
    }
}
