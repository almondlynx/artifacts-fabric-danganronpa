package artifacts.client.render.trinket;

import artifacts.client.render.trinket.model.HandsModel;
import artifacts.client.render.trinket.model.HeadModel;
import artifacts.client.render.trinket.model.LegsModel;
import artifacts.client.render.trinket.model.NecklaceModel;
import artifacts.client.render.trinket.model.ScarfModel;
import artifacts.client.render.trinket.renderer.*;
import artifacts.init.Items;
import net.minecraft.client.renderer.RenderType;
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
        renderers.put(Items.SKIRT, new BodyCurioRenderer());

        // head
        renderers.put(Items.PLASTIC_DRINKING_HAT, new HeadCurioRenderer());
        renderers.put(Items.NOVELTY_DRINKING_HAT, new BodyCurioRenderer());
        renderers.put(Items.SNORKEL, new SimpleCurioRenderer("snorkel", HeadModel.snorkel()));
        renderers.put(Items.NIGHT_VISION_GOGGLES, new GlowingCurioRenderer("night_vision_goggles", HeadModel.nightVisionGoggles()));
        renderers.put(Items.SUPERSTITIOUS_HAT, new SimpleCurioRenderer("superstitious_hat", HeadModel.superstitiousHat()));
        renderers.put(Items.VILLAGER_HAT, new SimpleCurioRenderer("villager_hat", HeadModel.villagerHat()));

        // hands
        renderers.put(Items.DIGGING_CLAWS, new GloveCurioRenderer("claws/digging_claws", "claws/digging_claws", HandsModel::claws));
        renderers.put(Items.FERAL_CLAWS, new GloveCurioRenderer("claws/feral_claws", "claws/feral_claws", HandsModel::claws));
        renderers.put(Items.POWER_GLOVE, new GloveCurioRenderer("power_glove"));
        renderers.put(Items.FIRE_GAUNTLET, new GlowingGloveCurioRenderer("fire_gauntlet"));
        renderers.put(Items.POCKET_PISTON, new GloveCurioRenderer("pocket_piston"));
        renderers.put(Items.VAMPIRIC_GLOVE, new GloveCurioRenderer("vampiric_glove"));
        renderers.put(Items.GOLDEN_HOOK, new GloveCurioRenderer("golden_hook", HandsModel::goldenHook));

        // feet
        renderers.put(Items.AQUA_DASHERS, new SimpleCurioRenderer("aqua_dashers", LegsModel.aquaDashers(1.25F)));
        renderers.put(Items.BUNNY_HOPPERS, new SimpleCurioRenderer("bunny_hoppers", LegsModel.bunnyHoppers()));
        renderers.put(Items.KITTY_SLIPPERS, new SimpleCurioRenderer("kitty_slippers", LegsModel.kittySlippers()));
        renderers.put(Items.RUNNING_SHOES, new SimpleCurioRenderer("running_shoes", LegsModel.shoes(0.5F)));
        renderers.put(Items.STEADFAST_SPIKES, new SimpleCurioRenderer("steadfast_spikes", LegsModel.steadfastSpikes()));
        renderers.put(Items.FLIPPERS, new SimpleCurioRenderer("flippers", LegsModel.flippers()));

    }
}
