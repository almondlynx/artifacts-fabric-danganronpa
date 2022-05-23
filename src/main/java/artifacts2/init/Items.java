package artifacts2.init;

import artifacts2.Artifacts;
import artifacts2.item.UmbrellaItem;
import artifacts2.item.curio.TrinketArtifactItem;
import artifacts2.item.curio.body.SkirtItem;
import artifacts2.item.curio.hands.DiggingClawsItem;
import artifacts2.item.curio.hands.FeralClawsItem;
import artifacts2.item.curio.hands.FireGauntletItem;
import artifacts2.item.curio.hands.PocketPistonItem;
import artifacts2.item.curio.hands.PowerGloveItem;
import artifacts2.item.curio.hands.VampiricGloveItem;
import artifacts2.item.curio.head.HairItem;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unused")
public class Items {

	public static final Item SKIRT = register("celes_skirt", new SkirtItem());
	public static final Item HAIR = register("hair", new HairItem());
	public static final Set<Item> LEG_LIMITING_ITEMS = new HashSet(Arrays.asList(SKIRT));

	public static final Item UMBRELLA = register("umbrella", new UmbrellaItem());

	// Hands
	public static final Item DIGGING_CLAWS = register("digging_claws", new DiggingClawsItem());
	public static final Item FERAL_CLAWS = register("feral_claws", new FeralClawsItem());
	public static final Item POWER_GLOVE = register("power_glove", new PowerGloveItem());
	public static final Item FIRE_GAUNTLET = register("fire_gauntlet", new FireGauntletItem());
	public static final Item POCKET_PISTON = register("pocket_piston", new PocketPistonItem());
	public static final Item VAMPIRIC_GLOVE = register("vampiric_glove", new VampiricGloveItem());
	public static final Item GOLDEN_HOOK = register("golden_hook", new TrinketArtifactItem(Slot.GLOVE_MAINHAND, Slot.GLOVE_OFFHAND));

	private static Item register(String name, Item item) {
		return Registry.register(Registry.ITEM, Artifacts.id(name), item);
	}

	private Items() {
	}
}
