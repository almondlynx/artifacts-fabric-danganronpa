package artifacts2.init;

import artifacts2.Artifacts;
import artifacts2.item.curio.body.SkirtItem;
import artifacts2.item.curio.head.HairItem;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unused")
public class Items {

	public static final Item SKIRT = register("skirt", new SkirtItem());
	public static final Item HAIR = register("hair", new HairItem());
	public static final Set<Item> LEG_LIMITING_ITEMS = new HashSet(Arrays.asList(SKIRT));

	private static Item register(String name, Item item) {
		return Registry.register(Registry.ITEM, Artifacts.id(name), item);
	}

	private Items() {
	}
}
