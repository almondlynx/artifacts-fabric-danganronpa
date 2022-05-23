package artifacts2.client.render.trinket.renderer;

import artifacts2.client.render.TrinketRenderHelper;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.function.Function;

public class SkirtCurioRenderer implements CurioRenderer {

    private static final HumanoidModel<LivingEntity> model = body(RenderType::entityCutoutNoCull, 32, 32, 0);
    private static HumanoidModel<LivingEntity> body(Function<ResourceLocation, RenderType> renderType, int textureWidth, int textureHeight, float delta) {
        HumanoidModel<LivingEntity> model = new HumanoidModel<>(renderType, 0, 0, textureWidth, textureHeight);
        model.setAllVisible(false);

        model.leftLeg = new ModelPart(model);
        model.rightLeg = new ModelPart(model);

        // legs
        model.leftLeg.texOffs(0, 0);
        model.leftLeg.addBox(-2, 0, -2, 4, 12, 4, delta);
        model.rightLeg.texOffs(16, 0);
        model.rightLeg.addBox(-2, 0, -2, 4, 12, 4, delta);

        return model;
    }

    public SkirtCurioRenderer()
    {

    }

    @Override
    public void render(String slot, int index, PoseStack poseStack, MultiBufferSource buffer, int light, LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ticks, float headYaw, float headPitch, ItemStack itemStack) {
        if (!itemStack.isEmpty()) {
            model.setupAnim(livingEntity, limbSwing, limbSwingAmount, ticks, headYaw, headPitch);
            model.prepareMobModel(livingEntity, limbSwing, limbSwingAmount, partialTicks);
            TrinketRenderHelper.followBodyRotations(livingEntity, model);

            poseStack.pushPose();

            if (livingEntity.isPassenger())
            {
                poseStack.mulPose(Vector3f.XP.rotationDegrees(-90f));
                poseStack.translate(0.0D, -.75D, .75D);
            }
            else if (livingEntity instanceof AbstractClientPlayer && ((AbstractClientPlayer)livingEntity).isCrouching())
            {
                poseStack.mulPose(Vector3f.XP.rotationDegrees(-45f));
                poseStack.translate(0.0D, -.5D, .375D);
            }

            model.body.translateAndRotate(poseStack);

            poseStack.translate(0.0D, -.25D, 0.0D);
            poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F));

            poseStack.scale(0.625F, -0.625F, -0.625F);
            Minecraft.getInstance().getItemInHandRenderer().renderItem(livingEntity, itemStack, ItemTransforms.TransformType.HEAD, false, poseStack, buffer, light);

            poseStack.popPose();
        }
    }
}
