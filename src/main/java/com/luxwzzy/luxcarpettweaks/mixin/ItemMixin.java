package com.luxwzzy.luxcarpettweaks.mixin;

import com.luxwzzy.luxcarpettweaks.LuxCarpetTweaksRules;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {

    @Inject(method = "getDestroySpeed", at = @At("HEAD"), cancellable = true)
    private void brokenToolMiningSpeed(ItemStack itemStack, BlockState state, CallbackInfoReturnable<Float> cir) {
        if (!LuxCarpetTweaksRules.unbreakableTools) return;
        if (itemStack.getDamageValue() >= itemStack.getMaxDamage() - 1) {
            cir.setReturnValue(1.0F);
        }
    }

    @Inject(method = "isCorrectToolForDrops", at = @At("HEAD"), cancellable = true)
    private void brokenToolDrops(ItemStack itemStack, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (!LuxCarpetTweaksRules.unbreakableTools) return;
        if (itemStack.getDamageValue() >= itemStack.getMaxDamage() - 1) {
            cir.setReturnValue(false);
        }
    }
}