package fr.eclipseria.wildernessboundbackport.item;

import fr.eclipseria.wildernessboundbackport.WildernessBoundBackport;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(WildernessBoundBackport.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
