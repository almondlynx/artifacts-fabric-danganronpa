package artifacts2.client.render.trinket.renderer;

import artifacts2.client.render.TrinketRenderHelper;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.function.Function;

public class HeadCurioRenderer implements CurioRenderer {

    private static final HumanoidModel<LivingEntity> model = head(RenderType::entityCutoutNoCull, 32, 32);
    private static HumanoidModel<LivingEntity> head(Function<ResourceLocation, RenderType> renderType, int textureWidth, int textureHeight) {
        HumanoidModel<LivingEntity> model = new HumanoidModel<>(renderType, 0, 0, textureWidth, textureHeight);
        model.setAllVisible(false);

        model.head = new ModelPart(model);

        // hat
        model.head.texOffs(0, 0);
        model.head.addBox(-4, -8, -4, 8, 8, 8, 0.5F);

        return model;
    }

    public HeadCurioRenderer()
    {

    }

    @Override
    public void render(String slot, int index, PoseStack poseStack, MultiBufferSource buffer, int light, LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ticks, float headYaw, float headPitch, ItemStack itemStack) {
        if (!itemStack.isEmpty()) {
            model.setupAnim(livingEntity, limbSwing, limbSwingAmount, ticks, headYaw, headPitch);
            model.prepareMobModel(livingEntity, limbSwing, limbSwingAmount, partialTicks);
            TrinketRenderHelper.followBodyRotations(livingEntity, model);

            poseStack.pushPose();

            ((HeadedModel) model).getHead().translateAndRotate(poseStack);
            poseStack.translate(0.0D, -0.25D, 0.0D);
            poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
            poseStack.scale(0.625F, -0.625F, -0.625F);
            Minecraft.getInstance().getItemInHandRenderer().renderItem(livingEntity, itemStack, ItemTransforms.TransformType.HEAD, false, poseStack, buffer, light);

            poseStack.popPose();
        }
    }
}
