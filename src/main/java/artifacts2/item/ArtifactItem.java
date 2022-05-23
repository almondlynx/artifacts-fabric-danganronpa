package artifacts2.item;

import artifacts2.Artifacts;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import java.util.Collections;
import java.util.List;

public abstract class ArtifactItem extends Item {

	public ArtifactItem(Properties properties) {
		super(properties.stacksTo(1).tab(Artifacts.ITEM_GROUP).rarity(Rarity.RARE));
	}

	public ArtifactItem() {
		this(new Properties());
	}

	protected List<String> getTooltipDescriptionArguments() {
		return Collections.emptyList();
	}
}
