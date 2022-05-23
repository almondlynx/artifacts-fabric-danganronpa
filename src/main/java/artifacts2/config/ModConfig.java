package artifacts2.config;

import artifacts2.Artifacts;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;

@Config(name = Artifacts.MOD_ID)
@Config.Gui.Background("minecraft:textures/block/mossy_cobblestone.png")
public final class ModConfig extends PartitioningSerializer.GlobalData {
	@ConfigEntry.Category("general")
	@ConfigEntry.Gui.TransitiveObject
	public General general = new General();

	private ModConfig() {
	}

	@Config(name = "general")
	public static final class General implements ConfigData {
		@SuppressWarnings("unused")
		@ConfigEntry.Gui.Excluded
		public int configVersion = 0;
		@ConfigEntry.Gui.Tooltip(count = 2)
		public int everlastingFoodCooldown = 300;
		@ConfigEntry.Gui.Tooltip(count = 2)
		public boolean playExtraHurtSounds = true;
		@ConfigEntry.Gui.Tooltip(count = 2)
		public boolean showFirstPersonGloves = true;
		@ConfigEntry.Gui.Tooltip(count = 2)
		public boolean showTooltips = true;

		private General() {
		}
	}

}
