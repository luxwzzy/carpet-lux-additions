package com.luxwzzy.luxcarpettweaks;

import carpet.api.settings.Rule;
import carpet.api.settings.RuleCategory;

public class LuxCarpetTweaksRules {

    @Rule(
        categories = RuleCategory.SURVIVAL,
        options = {"0", "100", "500", "1000"},
        strict = false
    )
    public static int ancientBartering = 0;

    @Rule(
        categories = RuleCategory.SURVIVAL
    )
    public static boolean oldWitchDrops = false;

    @Rule(
        categories = RuleCategory.SURVIVAL
    )
    public static boolean snowUnderTrees = true;

    @Rule(
        categories = RuleCategory.SURVIVAL
    )
    public static boolean waterInIceExplosion = false;

    @Rule(
            categories = RuleCategory.SURVIVAL
    )
    public static boolean unbreakableTools = false;
    @Rule(
            categories = RuleCategory.SURVIVAL
    )
    public static boolean aggroPiglinXpDrop = false;
}