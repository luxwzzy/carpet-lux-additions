package com.luxwzzy.luxcarpettweaks.mixin;

import com.luxwzzy.luxcarpettweaks.LuxCarpetTweaksRules;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(PiglinAi.class)
public class PiglinBrainMixin {

    @Inject(method = "getBarterResponseItems", at = @At("RETURN"), cancellable = true)
    private static void ancientBartering(Piglin piglin, CallbackInfoReturnable<List<ItemStack>> cir) {
        if (LuxCarpetTweaksRules.ancientBartering == 0) return;
        if (piglin.getRandom().nextInt(LuxCarpetTweaksRules.ancientBartering) == 0) {
            List<ItemStack> items = new ArrayList<>(cir.getReturnValue());
            items.add(new ItemStack(Items.ANCIENT_DEBRIS));
            cir.setReturnValue(items);
        }
    }
}