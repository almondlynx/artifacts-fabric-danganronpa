package artifacts.common.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.BunnyHoppersModel;
import artifacts.common.events.HandleFallDamageCallback;
import artifacts.common.events.PlayHurtSoundCallback;
import artifacts.common.init.Items;
import artifacts.common.trinkets.Slots;
import artifacts.common.trinkets.TrinketsHelper;
import dev.emi.trinkets.api.SlotGroups;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;

public class BunnyHoppersItem extends TrinketArtifactItem {

	private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/trinket/bunny_hoppers.png");
	private Object model;

	public BunnyHoppersItem() {
		super(new Item.Settings());
		PlayHurtSoundCallback.EVENT.register(BunnyHoppersItem::onPlayHurtSound);
		HandleFallDamageCallback.EVENT.register(BunnyHoppersItem::onHandleFallDamage);
	}

	@Override
	public StatusEffectInstance getPermanentEffect() {
		return new StatusEffectInstance(StatusEffects.JUMP_BOOST, 20, 1, true, false);
	}

	private static void onPlayHurtSound(LivingEntity entity, float volume, float pitch) {
		if (TrinketsHelper.isEquipped(Items.BUNNY_HOPPERS, entity)) {
			entity.playSound(SoundEvents.ENTITY_RABBIT_HURT, volume, pitch);
		}
	}

	private static ActionResult onHandleFallDamage(LivingEntity entity, float fallDistance, float damageMultiplier) {
		if (TrinketsHelper.isEquipped(Items.BUNNY_HOPPERS, entity)) {
			return ActionResult.FAIL;
		}

		return ActionResult.PASS;
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected BunnyHoppersModel getModel() {
		if (model == null) {
			model = new BunnyHoppersModel();
		}
		return (BunnyHoppersModel) model;
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getTexture() {
		return TEXTURE;
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.FEET) && slot.equals(Slots.SHOES);
	}
}
