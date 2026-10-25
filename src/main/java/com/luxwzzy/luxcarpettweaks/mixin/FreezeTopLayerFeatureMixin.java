package com.luxwzzy.luxcarpettweaks.mixin;

import com.luxwzzy.luxcarpettweaks.LuxCarpetTweaksRules;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.SnowAndFreezeFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(SnowAndFreezeFeature.class)
public class FreezeTopLayerFeatureMixin {

    @ModifyArg(method = "place", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/WorldGenLevel;getHeight(Lnet/minecraft/world/level/levelgen/Heightmap$Types;II)I"))
    private Heightmap.Types snowUnderTrees(Heightmap.Types heightmap) {
        if (!LuxCarpetTweaksRules.snowUnderTrees) return heightmap;
        return Heightmap.Types.MOTION_BLOCKING_NO_LEAVES;
    }
}