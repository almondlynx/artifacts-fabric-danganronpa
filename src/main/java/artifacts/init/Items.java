package artifacts.init;

import artifacts.Artifacts;
import artifacts.item.EverlastingFoodItem;
import artifacts.item.UmbrellaItem;
import artifacts.item.curio.TrinketArtifactItem;
import artifacts.item.curio.WhoopeeCushionItem;
import artifacts.item.curio.belt.AntidoteVesselItem;
import artifacts.item.curio.belt.CloudInABottleItem;
import artifacts.item.curio.belt.CrystalHeartItem;
import artifacts.item.curio.belt.HeliumFlamingoItem;
import artifacts.item.curio.belt.ObsidianSkullItem;
import artifacts.item.curio.belt.UniversalAttractorItem;
import artifacts.item.curio.feet.AquaDashersItem;
import artifacts.item.curio.feet.BunnyHoppersItem;
import artifacts.item.curio.feet.KittySlippersItem;
import artifacts.item.curio.feet.RunningShoesItem;
import artifacts.item.curio.feet.SteadfastSpikesItem;
import artifacts.item.curio.hands.DiggingClawsItem;
import artifacts.item.curio.hands.FeralClawsItem;
import artifacts.item.curio.hands.FireGauntletItem;
import artifacts.item.curio.hands.PocketPistonItem;
import artifacts.item.curio.hands.PowerGloveItem;
import artifacts.item.curio.hands.VampiricGloveItem;
import artifacts.item.curio.head.DrinkingHatItem;
import artifacts.item.curio.head.NightVisionGogglesItem;
import artifacts.item.curio.head.SnorkelItem;
import artifacts.item.curio.necklace.CharmOfSinkingItem;
import artifacts.item.curio.necklace.CrossNecklaceItem;
import artifacts.item.curio.necklace.FlamePendantItem;
import artifacts.item.curio.necklace.PanicNecklaceItem;
import artifacts.item.curio.necklace.ScarfOfInvisibilityItem;
import artifacts.item.curio.necklace.ShockPendantItem;
import artifacts.item.curio.necklace.ThornPendantItem;
import net.minecraft.core.Registry;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

@SuppressWarnings("unused")
public class Items {

	public static final Item UMBRELLA = register("umbrella", new UmbrellaItem());
	public static final Item WHOOPEE_CUSHION = register("whoopee_cushion", new WhoopeeCushionItem());

	// Food
	public static final Item EVERLASTING_BEEF = register("everlasting_beef", new EverlastingFoodItem(Foods.BEEF));
	public static final Item ETERNAL_STEAK = register("eternal_steak", new EverlastingFoodItem(Foods.COOKED_BEEF));

	// Head
	public static final Item PLASTIC_DRINKING_HAT = register("plastic_drinking_hat", new DrinkingHatItem());
	public static final Item NOVELTY_DRINKING_HAT = register("novelty_drinking_hat", new DrinkingHatItem());
	public static final Item SNORKEL = register("snorkel", new SnorkelItem());
	public static final Item NIGHT_VISION_GOGGLES = register("night_vision_goggles", new NightVisionGogglesItem());
	public static final Item VILLAGER_HAT = register("villager_hat", new TrinketArtifactItem(Slot.HAT));
	public static final Item SUPERSTITIOUS_HAT = register("superstitious_hat", new TrinketArtifactItem(Slot.HAT));

	// Necklace
	public static final Item LUCKY_SCARF = register("lucky_scarf", new TrinketArtifactItem(Slot.NECKLACE));
	public static final Item SCARF_OF_INVISIBILITY = register("scarf_of_invisibility", new ScarfOfInvisibilityItem());
	public static final Item CROSS_NECKLACE = register("cross_necklace", new CrossNecklaceItem());
	public static final Item PANIC_NECKLACE = register("panic_necklace", new PanicNecklaceItem());
	public static final Item SHOCK_PENDANT = register("shock_pendant", new ShockPendantItem());
	public static final Item FLAME_PENDANT = register("flame_pendant", new FlamePendantItem());
	public static final Item THORN_PENDANT = register("thorn_pendant", new ThornPendantItem());
	public static final Item CHARM_OF_SINKING = register("charm_of_sinking", new CharmOfSinkingItem());

	// Belt
	public static final Item OBSIDIAN_SKULL = register("obsidian_skull", new ObsidianSkullItem());
	public static final Item ANTIDOTE_VESSEL = register("antidote_vessel", new AntidoteVesselItem());
	public static final Item UNIVERSAL_ATTRACTOR = register("universal_attractor", new UniversalAttractorItem());
	public static final Item CRYSTAL_HEART = register("crystal_heart", new CrystalHeartItem());
	public static final Item CLOUD_IN_A_BOTTLE = register("cloud_in_a_bottle", new CloudInABottleItem());
	public static final Item HELIUM_FLAMINGO = register("helium_flamingo", new HeliumFlamingoItem());

	// Hands
	public static final Item DIGGING_CLAWS = register("digging_claws", new DiggingClawsItem());
	public static final Item FERAL_CLAWS = register("feral_claws", new FeralClawsItem());
	public static final Item POWER_GLOVE = register("power_glove", new PowerGloveItem());
	public static final Item FIRE_GAUNTLET = register("fire_gauntlet", new FireGauntletItem());
	public static final Item POCKET_PISTON = register("pocket_piston", new PocketPistonItem());
	public static final Item VAMPIRIC_GLOVE = register("vampiric_glove", new VampiricGloveItem());
	public static final Item GOLDEN_HOOK = register("golden_hook", new TrinketArtifactItem(Slot.GLOVE_MAINHAND, Slot.GLOVE_OFFHAND));

	// Feet
	public static final Item AQUA_DASHERS = register("aqua_dashers", new AquaDashersItem());
	public static final Item BUNNY_HOPPERS = register("bunny_hoppers", new BunnyHoppersItem());
	public static final Item KITTY_SLIPPERS = register("kitty_slippers", new KittySlippersItem());
	public static final Item RUNNING_SHOES = register("running_shoes", new RunningShoesItem());
	public static final Item STEADFAST_SPIKES = register("steadfast_spikes", new SteadfastSpikesItem());
	public static final Item FLIPPERS = register("flippers", new TrinketArtifactItem(Slot.SHOES));

	private static Item register(String name, Item item) {
		return Registry.register(Registry.ITEM, Artifacts.id(name), item);
	}

	private Items() {
	}
}
