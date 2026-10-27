package com.luxwzzy.luxcarpetaddition.mixin;

import com.luxwzzy.luxcarpetaddition.LuxCarpetAdditionRules;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.level.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class IceBlockMixin {

    @Inject(method = "wasExploded", at = @At("HEAD"))
    private void waterInIceExplosion(ServerLevel level, BlockPos pos, Explosion explosion, CallbackInfo ci) {
        if (!LuxCarpetAdditionRules.waterInIceExplosion) return;
        if (!((Object)this instanceof IceBlock)) return;
        if (level.dimension() == Level.NETHER) return;
        level.setBlock(pos, Blocks.WATER.defaultBlockState(), 3);
    }
}