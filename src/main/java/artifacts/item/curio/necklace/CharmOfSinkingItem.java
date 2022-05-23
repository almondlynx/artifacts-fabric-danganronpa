package artifacts.item.curio.necklace;


import artifacts.init.Slot;
import artifacts.item.curio.TrinketArtifactItem;
import artifacts.trinkets.TrinketsHelper;
import be.florens.expandability.api.fabric.PlayerSwimCallback;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class CharmOfSinkingItem extends TrinketArtifactItem {

    public CharmOfSinkingItem() {
        super(Slot.NECKLACE);
    }

}
