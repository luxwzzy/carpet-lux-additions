package com.luxwzzy.luxcarpettweaks.mixin;

import java.util.ArrayList;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.luxwzzy.luxcarpettweaks.LuxCarpetTweaksRules;

import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

@Mixin(PiglinBrain.class)
public class PiglinBrainMixin {

    @Inject(
        method = "getBarteredItem",
        at = @At("RETURN"),
        cancellable = true
    )
    private static void ancientBartering(
            PiglinEntity piglin,
            CallbackInfoReturnable<List<ItemStack>> cir
    ) {
    if (LuxCarpetTweaksRules.ancientBartering == 0) return;
    if (piglin.getRandom().nextInt(LuxCarpetTweaksRules.ancientBartering) == 0) {
        List<ItemStack> items = new ArrayList<>(cir.getReturnValue());
        items.add(new ItemStack(Items.ANCIENT_DEBRIS));
        cir.setReturnValue(items);
    }
    }
}