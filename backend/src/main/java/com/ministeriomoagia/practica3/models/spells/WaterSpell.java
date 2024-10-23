package com.ministeriomoagia.practica3.models.spells;

import com.ministeriomoagia.practica3.models.Spell;
import com.ministeriomoagia.practica3.models.SpellType;

public class WaterSpell extends Spell {

    public WaterSpell(String name, int requiredLevel, int experienceEarned) {
        super(name, SpellType.WATER, requiredLevel, experienceEarned);
    }
}
