package vict.millmix.mixin;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.millenaire.common.entity.MillVillager;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import zone.rong.mixinbooter.MixinLoader;

@Mixin(MillVillager.class)
public abstract class MixinMillVillager extends EntityCreature  {

    public MixinMillVillager(World world) {
        super(world); //hush compiler's cry for (unused) superclass constructor
    }
    @Inject(method="jumpToDest",at=@At(value="HEAD"),cancellable=true, remap=false)
    private void jumpToDestTest(CallbackInfo ci){
        EntityLivingBase target = this.getAttackTarget();
        if (target != null && target instanceof EntityPlayer) {
            ci.cancel();
        } 
    }
}
