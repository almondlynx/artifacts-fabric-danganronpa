package artifacts2.init;

import artifacts2.Artifacts;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import dev.emi.trinkets.api.TrinketSlots;
import net.minecraft.resources.ResourceLocation;

public enum Slot {
    HAT("hat", SlotGroups.HEAD, true),
    NECKLACE(Slots.NECKLACE, SlotGroups.CHEST),
    GLOVE_MAINHAND(Slots.GLOVES, SlotGroups.HAND),
    GLOVE_OFFHAND(Slots.GLOVES, SlotGroups.OFFHAND),
    BELT(Slots.BELT, SlotGroups.LEGS),
    SHOES("shoes", SlotGroups.FEET, true);

    private final String slotId;
    private final String groupId;
    private final ResourceLocation texture;

    Slot(String slotId, String groupId, boolean isModCustom) {
        this.slotId = slotId;
        this.groupId = groupId;
        this.texture = new ResourceLocation(isModCustom ? Artifacts.MOD_ID : "trinkets",
                String.format("textures/item/empty_trinket_slot_%s.png", slotId));
    }

    Slot(String slotId, String groupId) {
        this(slotId, groupId, false);
    }

    public String getSlotId() {
        return slotId;
    }

    public String getGroupId() {
        return groupId;
    }

    public static void registerAll() {
        for (Slot slot : values()) {
            TrinketSlots.addSlot(slot.groupId, slot.slotId, slot.texture);
        }
    }
}
