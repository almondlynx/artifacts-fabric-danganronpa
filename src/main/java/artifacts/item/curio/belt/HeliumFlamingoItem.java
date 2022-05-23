package artifacts.item.curio.belt;

import artifacts.Artifacts;
import artifacts.init.Slot;
import artifacts.item.curio.TrinketArtifactItem;
import net.minecraft.client.Minecraft;
import net.minecraft.locale.Language;
import net.minecraft.resources.ResourceLocation;

import java.util.Collections;
import java.util.List;


public class HeliumFlamingoItem extends TrinketArtifactItem {

	public static final ResourceLocation C2S_AIR_SWIMMING_ID = Artifacts.id("c2s_air_swimming");

	// TODO: config
	public static final int MAX_FLIGHT_TIME = 150;
	public static final int RECHARGE_TIME = 300;

	public HeliumFlamingoItem() {
		super(Slot.BELT);
	}

	@Override
	protected List<String> getTooltipDescriptionArguments() {
		String translationKey = Minecraft.getInstance().options.keySprint.saveString();
		return Collections.singletonList(Language.getInstance().getOrDefault(translationKey));
	}
}
