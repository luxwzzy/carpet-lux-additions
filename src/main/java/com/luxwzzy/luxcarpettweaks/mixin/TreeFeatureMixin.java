package com.luxwzzy.luxcarpettweaks.mixin;

import com.luxwzzy.luxcarpettweaks.LuxCarpetTweaksRules;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Set;

@Mixin(TreeFeature.class)
public class TreeFeatureMixin {

    @Inject(
        method = "generate(Lnet/minecraft/world/gen/feature/util/FeatureContext;)Z",
        locals = LocalCapture.CAPTURE_FAILHARD,
        at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/BlockBox;encompassPositions(Ljava/lang/Iterable;)Ljava/util/Optional;", shift = At.Shift.BEFORE)
    )
    private void snowOnLeaves(FeatureContext<TreeFeatureConfig> context, CallbackInfoReturnable<Boolean> cir, StructureWorldAccess world, Random random, BlockPos blockPos, TreeFeatureConfig treeFeatureConfig, Set<BlockPos> set, Set<BlockPos> set2, Set<BlockPos> set3, Set<BlockPos> set4) {
        if (!LuxCarpetTweaksRules.snowUnderTrees) return;
        for (BlockPos leafPos : set3) {
            BlockPos snowPos = leafPos.up();
            if (world.isAir(snowPos) && world.getBiome(snowPos).value().canSetSnow(world, snowPos)) {
                world.setBlockState(snowPos, Blocks.SNOW.getDefaultState(), 19);
            }
        }
    }
}