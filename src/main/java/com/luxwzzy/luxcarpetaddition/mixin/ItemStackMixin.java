package com.luxwzzy.luxcarpetaddition.mixin;

import com.luxwzzy.luxcarpetaddition.LuxCarpetAdditionRules;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.world.entity.EquipmentSlot;

import java.util.function.BiConsumer;

@Mixin(ItemStack.class)
public class ItemStackMixin {

    @ModifyVariable(method = "applyDamage", at = @At("HEAD"), argsOnly = true, ordinal = 0)
    private int capDamage(int newDamage) {
        if (!LuxCarpetAdditionRules.unbreakableTools) return newDamage;
        ItemStack self = (ItemStack)(Object)this;
        if (newDamage >= self.getMaxDamage()) {
            return self.getMaxDamage() - 1;
        }
        return newDamage;
    }

    @Inject(method = "forEachModifier(Lnet/minecraft/world/entity/EquipmentSlot;Ljava/util/function/BiConsumer;)V", at = @At("HEAD"), cancellable = true)
    private void brokenArmorModifiersSlot(EquipmentSlot slot, BiConsumer consumer, CallbackInfo ci) {
        if (!LuxCarpetAdditionRules.unbreakableTools) return;
        ItemStack self = (ItemStack)(Object)this;
        if (self.getDamageValue() >= self.getMaxDamage() - 1) {
            ci.cancel();
        }
    }
}