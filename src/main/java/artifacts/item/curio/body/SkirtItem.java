package artifacts.item.curio.body;

import artifacts.item.curio.TrinketArtifactItem;
import dev.emi.trinkets.api.SlotGroups;
import net.minecraft.world.item.Wearable;

public class SkirtItem extends TrinketArtifactItem {
    @Override
    public boolean canWearInSlot(String group, String slot) {
        return group == SlotGroups.LEGS;
    }
}
