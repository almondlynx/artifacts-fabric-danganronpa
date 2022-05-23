package artifacts.item;

import artifacts.Artifacts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.ChatFormatting;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

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
