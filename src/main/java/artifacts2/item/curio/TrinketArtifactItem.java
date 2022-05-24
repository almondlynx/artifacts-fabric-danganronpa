package artifacts2.item.curio;

import artifacts2.Artifacts;
import artifacts2.client.render.trinket.CurioRenderers;
import artifacts2.init.Slot;
import artifacts2.item.ArtifactItem;
import artifacts2.trinkets.TrinketsHelper;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.emi.trinkets.api.Trinket;
import dev.emi.trinkets.api.TrinketItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class TrinketArtifactItem extends ArtifactItem implements Trinket {

	private final Set<Slot> equippableSlots;

	public TrinketArtifactItem(Slot... equippableSlots) {
		this.equippableSlots = Sets.newHashSet(equippableSlots);
		DispenserBlock.registerBehavior(this, TrinketItem.TRINKET_DISPENSER_BEHAVIOR);
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return equippableSlots.stream().anyMatch(equippableSlots ->
				equippableSlots.getSlotId().equals(slot) && equippableSlots.getGroupId().equals(group));
	}

	@Override
	public void tick(Player player, ItemStack stack) {
			curioTick(player, stack);
	}

	protected void curioTick(LivingEntity livingEntity, ItemStack stack) {
	}

	public MobEffectInstance getPermanentEffect() {
		return null;
	}

	@Environment(EnvType.CLIENT)
	public void render(String slot, PoseStack matrices, MultiBufferSource buffer, int light, AbstractClientPlayer player, float limbAngle,
					   float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch, ItemStack stack) {
		// TODO: support slot index (used by belt renderer)
		CurioRenderers.getRenderer(this).render(slot, 0, matrices, buffer, light, player, limbAngle,
				limbDistance, tickDelta, animationProgress, headYaw, headPitch, stack);
	}
}
