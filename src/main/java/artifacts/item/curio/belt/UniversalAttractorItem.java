package artifacts.item.curio.belt;

import artifacts.init.Slot;
import artifacts.item.curio.TrinketArtifactItem;
import artifacts.mixin.mixins.accessors.ItemEntityAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class UniversalAttractorItem extends TrinketArtifactItem {

    public UniversalAttractorItem() {
        super(Slot.BELT);
    }

    @Override
	// Magnet logic from Botania, see https://github.com/Vazkii/Botania
	protected void curioTick(LivingEntity livingEntity, ItemStack stack) {
	}
}
