package com.luxwzzy.luxcarpettweaks.mixin;

import com.luxwzzy.luxcarpettweaks.LuxCarpetTweaksRules;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.IceBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class IceBlockMixin {

    @Inject(method = "onDestroyedByExplosion", at = @At("HEAD"))
    private void waterInIceExplosion(ServerWorld world, BlockPos pos, Explosion explosion, CallbackInfo ci) {
        if (!LuxCarpetTweaksRules.waterInIceExplosion) return;
        if (!((Object)this instanceof IceBlock)) return;
        if (world.getRegistryKey() == net.minecraft.world.World.NETHER) return;
        world.setBlockState(pos, Blocks.WATER.getDefaultState());
    }
}