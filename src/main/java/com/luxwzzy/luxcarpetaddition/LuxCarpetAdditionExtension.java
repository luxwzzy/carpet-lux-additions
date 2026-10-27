package com.luxwzzy.luxcarpetaddition;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import net.fabricmc.api.ModInitializer;
import java.util.Map;

public class LuxCarpetAdditionExtension implements CarpetExtension, ModInitializer {

    @Override
    public void onInitialize() {
        CarpetServer.manageExtension(this);
    }

    @Override
    public void onGameStarted() {
        CarpetServer.settingsManager.parseSettingsClass(LuxCarpetAdditionRules.class);
    }

    @Override
    public Map<String, String> canHasTranslations(String lang) {
        return carpet.utils.Translations.getTranslationFromResourcePath("assets/lux-carpet-addition/lang/" + lang + ".json");
    }

    @Override
    public String version() {
        return "lux-carpet-tweaks-1.2.0";
    }
}