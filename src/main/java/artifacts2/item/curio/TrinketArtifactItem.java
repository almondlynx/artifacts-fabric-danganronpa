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
	public InteractionResultHolder<ItemStack> use(Level level, Player user, InteractionHand hand) {
		// Cycle artifact status when sneak right-clicking
		if (user.isShiftKeyDown()) {
			ItemStack stack = user.getItemInHand(hand);
			CompoundTag tag = stack.getOrCreateTagElement("Artifacts");
			tag.putByte("Status", (byte) ArtifactStatus.nextIndex(tag.getByte("Status")));
			stack.addTagElement("Artifacts", tag);

			if (level.isClientSide()) {
				// Show enabled/disabled message above hotbar
				ChatFormatting enabledColor = TrinketsHelper.areEffectsEnabled(stack) ? ChatFormatting.GREEN : ChatFormatting.RED;
				Component enabledText = new TranslatableComponent(getEffectsEnabledLanguageKey(stack)).withStyle(enabledColor);
				Minecraft.getInstance().gui.setOverlayMessage(enabledText, false);
			}

			return InteractionResultHolder.success(stack);
		}

		InteractionResultHolder<ItemStack> actionResult = Trinket.equipTrinket(user, hand);

		// Play right click equip sound
		if (actionResult.getResult().consumesAction()) {
			SoundInfo sound = this.getEquipSound();
			user.playSound(sound.getSoundEvent(), sound.getVolume(), sound.getPitch());
		}

		return actionResult;
	}

	@Override
	public void tick(Player player, ItemStack stack) {
		if (TrinketsHelper.areEffectsEnabled(stack)) {
			curioTick(player, stack);
		}
	}

	protected void curioTick(LivingEntity livingEntity, ItemStack stack) {
	}

	@Override
	public final Multimap<Attribute, AttributeModifier> getTrinketModifiers(String group, String slot, UUID uuid, ItemStack stack) {
		if (TrinketsHelper.areEffectsEnabled(stack)) {
			return this.applyModifiers(group, slot, uuid, stack);
		}
		return HashMultimap.create();
	}

	protected Multimap<Attribute, AttributeModifier> applyModifiers(String group, String slot, UUID uuid, ItemStack stack) {
		return HashMultimap.create();
	}

	@Override
	public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flags) {
		super.appendHoverText(stack, world, tooltip, flags);
		MutableComponent enabled = new TranslatableComponent(getEffectsEnabledLanguageKey(stack)).withStyle(ChatFormatting.GOLD);
		Component togglekeybind = new TranslatableComponent("artifacts.trinket.togglekeybind").withStyle(ChatFormatting.GRAY);
		tooltip.add(enabled.append(" ").append(togglekeybind));
	}

	/**
	 * @return The {@link SoundEvent} to play when the artifact is right-click equipped
	 */
	protected SoundInfo getEquipSound() {
		return new SoundInfo(SoundEvents.ARMOR_EQUIP_GENERIC);
	}

	/**
	 * @return An extra {@link SoundEvent} to play when an entity wearing this artifact is hurt
	 */
	protected SoundEvent getExtraHurtSound() {
		return null;
	}

	/**
	 * Used to give a Trinket a permanent status effect while wearing it.
	 * The StatusEffectInstance is applied every 15 ticks so a duration greater than that is required.
	 *
	 * @return The {@link MobEffectInstance} to be applied while wearing this artifact
	 */
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

	private void playExtraHurtSound(LivingEntity entity, float volume, float pitch) {
		if (Artifacts.CONFIG.general.playExtraHurtSounds) {
			SoundEvent hurtSound = getExtraHurtSound();

			if (hurtSound != null && TrinketsHelper.isEquipped(this, entity, true)) {
				entity.playSound(hurtSound, volume, pitch);
			}
		}
	}

	public static void addModifier(AttributeInstance instance, AttributeModifier modifier) {
		if (!instance.hasModifier(modifier)) {
			instance.addTransientModifier(modifier);
		}
	}

	public static void removeModifier(AttributeInstance instance, AttributeModifier modifier) {
		if (instance.hasModifier(modifier)) {
			instance.removeModifier(modifier);
		}
	}

	private static String getEffectsEnabledLanguageKey(ItemStack stack) {
		return TrinketsHelper.areEffectsEnabled(stack) ? "artifacts.trinket.effectsenabled" : "artifacts.trinket.effectsdisabled";
	}

	public enum ArtifactStatus {
		ALL_ENABLED(true, true),
		COSMETIC_ONLY(false, true);
		// EFFECTS_ONLY(true, false);

		private final boolean hasEffects;
		private final boolean hasCosmetics;

		ArtifactStatus(boolean hasEffects, boolean hasCosmetics) {
			this.hasEffects = hasEffects;
			this.hasCosmetics = hasCosmetics;
		}

		public boolean hasEffects() {
			return hasEffects;
		}

		public boolean hasCosmetics() {
			return hasCosmetics;
		}

		public static int nextIndex(int index) {
			return index >= values().length - 1 ? 0 : index + 1;
		}
	}

	// From Curios
	// TODO: Java 17 Record
	protected static final class SoundInfo {
		final SoundEvent soundEvent;
		final float volume;
		final float pitch;

		public SoundInfo(SoundEvent soundEvent) {
			this(soundEvent, 1f, 1f);
		}

		public SoundInfo(SoundEvent soundEvent, float volume, float pitch) {
			this.soundEvent = soundEvent;
			this.volume = volume;
			this.pitch = pitch;
		}

		public SoundEvent getSoundEvent() {
			return this.soundEvent;
		}

		public float getVolume() {
			return this.volume;
		}

		public float getPitch() {
			return this.pitch;
		}
	}
}
