package vict.millmix;

import org.spongepowered.asm.mixin.Mixins;
import zone.rong.mixinbooter.MixinLoader;

@MixinLoader
public class MillMixLoader {

    {
    Mixins.addConfiguration("millmix.mixins.json");
    }
}
