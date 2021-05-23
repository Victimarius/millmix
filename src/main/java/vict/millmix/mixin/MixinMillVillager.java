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

import org.millenaire.common.utilities.Point;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.BlockDoor;
import  net.minecraft.block.Block ;
import net.minecraft.block.properties.IProperty;
import net.minecraft.util.math.BlockPos;

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
    /* idea: change millvillager's toggleDoor to use BlockDoor's toggleDoor
     * @author Vict
     * @reason toggleDoor doesn't make use of BlockDoor.toggleDoor(),
     * causing issues when other mods override the behaviour of doors.
     * This way we don't manually set block states, but instead tell
     * the blocks to change their staes.
     */
    @Overwrite(remap=false)
    private void toggleDoor(Point p) {
        if (p == null) {return;}
        IBlockState state = p.getBlockActualState(this.world);
        boolean isOpen = state.getValue((IProperty)BlockDoor.OPEN).equals(true);
        BlockPos doorPos = p.getBlockPos();
        Block block = p.getBlock(world);
        if (block != null && block instanceof BlockDoor) {
            BlockDoor door = (BlockDoor)block;
            door.toggleDoor(world,doorPos, !isOpen);
        }
    }
}
