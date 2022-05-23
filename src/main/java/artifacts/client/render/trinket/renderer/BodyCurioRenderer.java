package artifacts.client.render.trinket.renderer;

import artifacts.client.render.TrinketRenderHelper;
import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.AbstractSkullBlock;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;
import java.util.function.Function;

public class BodyCurioRenderer implements CurioRenderer {

    private static final HumanoidModel<LivingEntity> model = body(RenderType::entityCutoutNoCull, 32, 32);
    private static HumanoidModel<LivingEntity> body(Function<ResourceLocation, RenderType> renderType, int textureWidth, int textureHeight) {
        HumanoidModel<LivingEntity> model = new HumanoidModel<>(renderType, 0, 0, textureWidth, textureHeight);
        model.setAllVisible(false);

        ModelPart body = new ModelPart(model);

        // belt
        body.texOffs(0, 0);
        body.addBox(-4, 0, -2, 8, 12, 4, 0.5F);
        return model;
    }

    public BodyCurioRenderer()
    {

    }

    @Override
    public void render(String slot, int index, PoseStack poseStack, MultiBufferSource buffer, int light, LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ticks, float headYaw, float headPitch, ItemStack itemStack) {
        if (!itemStack.isEmpty()) {
            model.setupAnim(livingEntity, limbSwing, limbSwingAmount, ticks, headYaw, headPitch);
            model.prepareMobModel(livingEntity, limbSwing, limbSwingAmount, partialTicks);
            TrinketRenderHelper.followBodyRotations(livingEntity, model);

            poseStack.pushPose();

            model.body.translateAndRotate(poseStack);
            poseStack.translate(0.0D, -.25D, 0.0D);
            poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
            poseStack.scale(0.625F, -0.625F, -0.625F);
            Minecraft.getInstance().getItemInHandRenderer().renderItem(livingEntity, itemStack, ItemTransforms.TransformType.HEAD, false, poseStack, buffer, light);

            poseStack.popPose();
        }
    }
}
