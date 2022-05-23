package artifacts.item.curio.feet;

import artifacts.init.Items;
import artifacts.init.Slot;
import artifacts.item.curio.TrinketArtifactItem;
import artifacts.trinkets.TrinketsHelper;
import be.florens.expandability.api.fabric.LivingFluidCollisionCallback;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.material.FluidState;

public class AquaDashersItem extends TrinketArtifactItem {

	public AquaDashersItem() {
		super(Slot.SHOES);
	}

	@Override
	public void tick(Player player, ItemStack stack) {

		super.tick(player, stack);
	}

}
