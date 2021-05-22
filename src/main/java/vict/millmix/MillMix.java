package vict.millmix;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;



import java.lang.ClassLoader;
import java.security.SecureClassLoader;
import java.net.URL;
import java.net.URLClassLoader;
import org.spongepowered.asm.mixin.Mixins;
import zone.rong.mixinbooter.MixinLoader;
import vict.millmix.command.CommandGetReputation;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;


@Mod(modid = MillMix.MOD_ID, dependencies="required-after:millenaire")
public class MillMix {
    public static final String MOD_ID = "millmix";

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        
    }
    
    @EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandGetReputation());
    }
    
}
