package artifacts2.item.curio.body;

import artifacts2.item.curio.TrinketArtifactItem;
import dev.emi.trinkets.api.SlotGroups;
import net.minecraft.world.item.Wearable;

public class BackpackItem extends TrinketArtifactItem {
    @Override
    public boolean canWearInSlot(String group, String slot) {
        return group == SlotGroups.CHEST;
    }
}