package com.ministeriomoagia.practica3.models.spells;

import com.ministeriomoagia.practica3.models.Spell;
import com.ministeriomoagia.practica3.models.SpellType;

public class DarknessSpell extends Spell {

    public DarknessSpell(String name, int requiredLevel, int experienceEarned) {
        super(name, SpellType.DARKNESS, requiredLevel, experienceEarned);
    }
}
