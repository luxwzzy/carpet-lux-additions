package com.luxwzzy.luxcarpettweaks;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.api.settings.SettingsManager;
import net.fabricmc.api.ModInitializer;

public class LuxCarpetTweaksExtension implements CarpetExtension, ModInitializer {

    public static final SettingsManager SETTINGS = new SettingsManager("1.0.0", "lux-carpet-tweaks", "Lux's Carpet Tweaks");

    @Override
    public void onInitialize() {
        CarpetServer.manageExtension(this);
    }

    @Override
    public void onGameStarted() {
        carpet.utils.Translations.registerFallbackTranslation(
            "lux-carpet-tweaks.rule.ancientBartering.desc",
            "1-in-N chance for piglins to drop ancient debris when bartering. 0 disables."
        );
        carpet.utils.Translations.registerFallbackTranslation(
            "lux-carpet-tweaks.rule.oldWitchDrops.desc",
            "Restores witch drops to pre-1.21 behavior (0-2 redstone instead of guaranteed 4-8)."
        );
        carpet.utils.Translations.registerFallbackTranslation(
            "lux-carpet-tweaks.rule.snowUnderTrees.desc",
            "Fixes snow not generating under trees (MC-119599)."
        );
        carpet.utils.Translations.registerFallbackTranslation(
            "lux-carpet-tweaks.rule.waterInIceExplosion.desc",
            "Ice blocks destroyed by explosions are replaced with water."
        );
        carpet.utils.Translations.registerFallbackTranslation(
                "lux-carpet-tweaks.rule.unbreakableTools.desc",
                "Tools and armor stay at 1 durability instead of breaking."
        );
        carpet.utils.Translations.registerFallbackTranslation(
                "lux-carpet-tweaks.rule.aggroPiglinXpDrop.desc",
                "Angry zombified piglins always drop XP when killed, regardless of cause of death."
        );
        SETTINGS.parseSettingsClass(LuxCarpetTweaksRules.class);
    }

    @Override
    public SettingsManager extensionSettingsManager() {
        return SETTINGS;
    }

    @Override
    public String version() {
        return "lux-carpet-tweaks-1.0.0";
    }
}