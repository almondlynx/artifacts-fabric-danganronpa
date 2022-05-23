package artifacts2.item.curio.hands;

import artifacts2.Artifacts;
import artifacts2.init.Slot;
import artifacts2.item.curio.TrinketArtifactItem;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

import java.util.UUID;

public class PowerGloveItem extends TrinketArtifactItem {

	public PowerGloveItem() {
		super(Slot.GLOVE_MAINHAND, Slot.GLOVE_OFFHAND);
	}

	@Override
	protected Multimap<Attribute, AttributeModifier> applyModifiers(String group, String slot, UUID uuid, ItemStack stack) {
		Multimap<Attribute, AttributeModifier> result = super.applyModifiers(group, slot, uuid, stack);
		AttributeModifier modifier = new AttributeModifier(uuid,
				Artifacts.id("power_glove_attack_damage").toString(),
				4, AttributeModifier.Operation.ADDITION);
		result.put(Attributes.ATTACK_DAMAGE, modifier);
		return result;
	}
}
