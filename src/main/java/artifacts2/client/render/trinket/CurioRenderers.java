package artifacts2.client.render.trinket;

import artifacts2.client.render.trinket.renderer.*;
import artifacts2.init.Items;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CurioRenderers {

    private static final Map<Item, CurioRenderer> renderers = new HashMap<>();

    public static CurioRenderer getRenderer(Item curio) {
        return renderers.get(curio);
    }

    public static void setupCurioRenderers() {
        renderers.put(Items.SKIRT, new SkirtCurioRenderer());
        renderers.put(Items.BACKPACK, new BodyCurioRenderer());
        renderers.put(Items.HAIR, new HeadCurioRenderer());
    }
}
