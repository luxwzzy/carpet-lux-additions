package com.luxwzzy.luxcarpettweaks.mixin;

import com.luxwzzy.luxcarpettweaks.LuxCarpetTweaksRules;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.zombie.ZombifiedPiglin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import net.minecraft.world.entity.NeutralMob;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "isAlwaysExperienceDropper", at = @At("HEAD"), cancellable = true)
    private void angryZombifiedPiglinsDropXp(CallbackInfoReturnable<Boolean> cir) {
        if (!LuxCarpetTweaksRules.aggroPiglinXpDrop) return;
        if ((Object) this instanceof ZombifiedPiglin piglin) {
            if (((NeutralMob) piglin).isAngry() && !piglin.isBaby()) {
                cir.setReturnValue(true);
            }
        }
    }
}