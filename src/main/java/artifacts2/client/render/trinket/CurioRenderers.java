package artifacts2.client.render.trinket;

import artifacts2.client.render.trinket.model.HandsModel;
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

    public static Optional<GloveCurioRenderer> getGloveRenderer(ItemStack stack) {
        if (!stack.isEmpty()) {
            CurioRenderer renderer = getRenderer(stack.getItem());
            if (renderer instanceof GloveCurioRenderer) {
                return Optional.of((GloveCurioRenderer) renderer);
            }
        }
        return Optional.empty();
    }

    public static void setupCurioRenderers() {
        renderers.put(Items.SKIRT, new SkirtCurioRenderer());
        renderers.put(Items.HAIR, new HeadCurioRenderer());

        // hands
        renderers.put(Items.DIGGING_CLAWS, new GloveCurioRenderer("claws/digging_claws", "claws/digging_claws", HandsModel::claws));
        renderers.put(Items.FERAL_CLAWS, new GloveCurioRenderer("claws/feral_claws", "claws/feral_claws", HandsModel::claws));
        renderers.put(Items.POWER_GLOVE, new GloveCurioRenderer("power_glove"));
        renderers.put(Items.FIRE_GAUNTLET, new GlowingGloveCurioRenderer("fire_gauntlet"));
        renderers.put(Items.POCKET_PISTON, new GloveCurioRenderer("pocket_piston"));
        renderers.put(Items.VAMPIRIC_GLOVE, new GloveCurioRenderer("vampiric_glove"));
        renderers.put(Items.GOLDEN_HOOK, new GloveCurioRenderer("golden_hook", HandsModel::goldenHook));
    }
}
