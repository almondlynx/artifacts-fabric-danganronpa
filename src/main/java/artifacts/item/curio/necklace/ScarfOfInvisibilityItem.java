package artifacts.item.curio.necklace;

import artifacts.item.curio.TrinketArtifactItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class ScarfOfInvisibilityItem extends TrinketArtifactItem {

    @Override
	public MobEffectInstance getPermanentEffect() {
		return new MobEffectInstance(MobEffects.INVISIBILITY, 20, 0, true, false);
	}
}
