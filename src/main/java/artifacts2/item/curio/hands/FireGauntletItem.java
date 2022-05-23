package artifacts2.item.curio.hands;

import artifacts2.init.Slot;
import artifacts2.item.curio.TrinketArtifactItem;
import net.minecraft.sounds.SoundEvents;

public class FireGauntletItem extends TrinketArtifactItem {

    public FireGauntletItem() {
        super(Slot.GLOVE_MAINHAND, Slot.GLOVE_OFFHAND);
    }

    @Override
	protected SoundInfo getEquipSound() {
		return new SoundInfo(SoundEvents.ARMOR_EQUIP_IRON);
	}
}
