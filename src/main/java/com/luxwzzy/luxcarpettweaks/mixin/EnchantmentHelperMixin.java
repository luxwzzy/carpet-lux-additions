package com.luxwzzy.luxcarpettweaks.mixin;

import com.luxwzzy.luxcarpettweaks.LuxCarpetTweaksRules;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {
    @Inject(method = "getItemEnchantmentLevel", at = @At("HEAD"), cancellable = true)
    private static void disableBrokenItemEnchantments(Holder<Enchantment> enchantment, ItemInstance piece, CallbackInfoReturnable<Integer> cir) {
        if (!LuxCarpetTweaksRules.unbreakableTools) return;

        // Convertimos la interfaz a ItemStack para leer la durabilidad
        ItemStack stack = (ItemStack) piece;

        // isDamageableItem() previene crasheos si el juego revisa ítems sin durabilidad
        if (stack.isDamageableItem() && stack.getDamageValue() >= stack.getMaxDamage() - 1) {
            if (enchantment.is(net.minecraft.world.item.enchantment.Enchantments.MENDING)) return;
            cir.setReturnValue(0);
        }
    }
}