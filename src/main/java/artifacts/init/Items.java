package artifacts.init;

import artifacts.Artifacts;
import artifacts.item.UmbrellaItem;
import artifacts.item.curio.TrinketArtifactItem;
import artifacts.item.curio.body.SkirtItem;
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
import net.minecraft.core.Registry;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;

@SuppressWarnings("unused")
public class Items {

	public static final Item SKIRT = register("celes_skirt", new SkirtItem());

	public static final Item UMBRELLA = register("umbrella", new UmbrellaItem());

	// Head
	public static final Item PLASTIC_DRINKING_HAT = register("plastic_drinking_hat", new DrinkingHatItem());
	public static final Item NOVELTY_DRINKING_HAT = register("novelty_drinking_hat", new DrinkingHatItem());
	public static final Item SNORKEL = register("snorkel", new SnorkelItem());
	public static final Item NIGHT_VISION_GOGGLES = register("night_vision_goggles", new NightVisionGogglesItem());
	public static final Item VILLAGER_HAT = register("villager_hat", new TrinketArtifactItem(Slot.HAT));
	public static final Item SUPERSTITIOUS_HAT = register("superstitious_hat", new TrinketArtifactItem(Slot.HAT));

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
