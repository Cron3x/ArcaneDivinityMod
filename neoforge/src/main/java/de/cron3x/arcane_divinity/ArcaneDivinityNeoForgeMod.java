package de.cron3x.arcane_divinity;

import de.cron3x.arcane_divinity.block.BlockRegister;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class ArcaneDivinityNeoForgeMod {
    public ArcaneDivinityNeoForgeMod(IEventBus eventBus) {

        BlockRegister.BLOCKS.register(eventBus);
        // This method is invoked by the NeoForge mod loader when it is ready
        // to load your mod. You can access NeoForge and Common code in this
        // project.

        // Use NeoForge to bootstrap the Common mod.
        Constants.LOG.info("Hello NeoForge world!");
        CommonClass.init();

    }

}
