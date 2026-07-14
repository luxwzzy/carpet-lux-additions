package com.luxwzzy.luxcarpettweaks.mixin;

import com.luxwzzy.luxcarpettweaks.LuxCarpetTweaksRules;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class WitchEntityMixin {

@Inject(
    method = "dropLoot",
    at = @At("HEAD"),
    cancellable = true
)
private void oldWitchDrops(ServerWorld world, DamageSource source, boolean causedByPlayer, CallbackInfo ci) {
    if (!LuxCarpetTweaksRules.oldWitchDrops) return;
    if (!((Object)this instanceof WitchEntity self)) return;

    int rolls = 1 + world.random.nextInt(3);
    Item[] pool = {
        Items.STICK, Items.STICK,
        Items.REDSTONE,
        Items.GUNPOWDER,
        Items.GLOWSTONE_DUST,
        Items.SUGAR,
        Items.SPIDER_EYE,
        Items.GLASS_BOTTLE
    };
    for (int i = 0; i < rolls; i++) {
        Item item = pool[world.random.nextInt(pool.length)];
        int count = world.random.nextInt(3);
        if (count > 0) {
            self.dropStack(world, new ItemStack(item, count));
        }
    }
    ci.cancel();
}
}