package artifacts2.item.curio.head;

import artifacts2.item.curio.TrinketArtifactItem;
import dev.emi.trinkets.api.SlotGroups;
import net.minecraft.world.item.Wearable;

public class HairItem  extends TrinketArtifactItem implements Wearable {
    @Override
    public boolean canWearInSlot(String group, String slot) {
        return group == SlotGroups.HEAD;
    }
}
