package artifacts2.trinkets;

import artifacts2.item.curio.TrinketArtifactItem;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public final class TrinketsHelper {

	private TrinketsHelper() {
	}

	public static boolean isEquipped(Item item, LivingEntity entity) {
		return isEquipped(item, entity, false);
	}

	public static boolean isEquipped(Predicate<ItemStack> filter, LivingEntity entity) {
		return isEquipped(filter, entity, false);
	}

	public static List<ItemStack> getAllEquipped(LivingEntity entity) {
		return getAllEquipped(entity, false);
	}

	public static boolean isEquipped(Item item, LivingEntity entity, boolean ignoreEffectsDisabled) {
		return isEquipped(stack -> stack.getItem().equals(item), entity, ignoreEffectsDisabled);
	}

	public static boolean isEquipped(Predicate<ItemStack> filter, LivingEntity entity, boolean ignoreEffectsDisabled) {
		for (ItemStack stack : getAllEquipped(entity, ignoreEffectsDisabled)) {
			if (filter.test(stack)) {
				return true;
			}
		}

		return false;
	}

	public static List<ItemStack> getAllEquipped(LivingEntity entity, boolean ignoreEffectsDisabled) {
		List<ItemStack> stacks = new ArrayList<>();

		// LivingEntity not currently supported by Trinkets
		if (entity instanceof Player) {
			Container inventory = TrinketsApi.getTrinketsInventory((Player) entity);

			for (int i = 0; i < inventory.getContainerSize(); i++) {
				ItemStack stack = inventory.getItem(i);

				if (!stack.isEmpty() && stack.getItem() instanceof TrinketArtifactItem) {
					stacks.add(stack);
				}
			}
		}

		return stacks;
	}

}
