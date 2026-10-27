package com.luxwzzy.luxcarpetaddition.mixin;

import com.luxwzzy.luxcarpetaddition.LuxCarpetAdditionRules;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class WitchEntityMixin {

    @Inject(method = "dropFromLootTable", at = @At("HEAD"), cancellable = true)
    private void oldWitchDrops(ServerLevel level, DamageSource damageSource, boolean hitByPlayer, CallbackInfo ci) {
        if (!LuxCarpetAdditionRules.oldWitchDrops) return;
        if (!((Object)this instanceof Witch self)) return;

        int rolls = 1 + level.getRandom().nextInt(3);
        net.minecraft.world.item.Item[] pool = {
                Items.STICK, Items.STICK,
                Items.REDSTONE,
                Items.GUNPOWDER,
                Items.GLOWSTONE_DUST,
                Items.SUGAR,
                Items.SPIDER_EYE,
                Items.GLASS_BOTTLE
        };
        for (int i = 0; i < rolls; i++) {
            net.minecraft.world.item.Item item = pool[level.getRandom().nextInt(pool.length)];
            int count = level.getRandom().nextInt(3);
            if (count > 0) {
                self.spawnAtLocation(level, new ItemStack(item, count));
            }
        }
        ci.cancel();
    }
}