package artifacts;

import artifacts.config.ModConfig;
import artifacts.init.Items;
import artifacts.init.Slot;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Artifacts implements ModInitializer {

	public static final String MOD_ID = "artifacts_2";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
	public static final CreativeModeTab ITEM_GROUP = FabricItemGroupBuilder.build(
			id("item_group"),
			() -> new ItemStack(Items.BUNNY_HOPPERS)
	);
	public static ModConfig CONFIG;

	@Override
	@SuppressWarnings("ResultOfMethodCallIgnored")
	public void onInitialize() {
		// Config
		Artifacts.CONFIG = AutoConfig.register(ModConfig.class,
				PartitioningSerializer.wrap(Toml4jConfigSerializer::new)).getConfig();

		// Trinkets setup
		Slot.registerAll();

		// Force loading init classes
		// Entities is loaded by items, loot tables can load lazily (no registration)
		Items.ANTIDOTE_VESSEL.toString();
		LOGGER.info("[Artifacts 2] Finished initialization");
	}

	public static ResourceLocation id(String path) {
		return new ResourceLocation(MOD_ID, path);
	}
}
