package com.ministeriomoagia.practica3.models.spells;

import com.ministeriomoagia.practica3.models.Spell;
import com.ministeriomoagia.practica3.models.SpellType;

public class WindSpell extends Spell {

    public WindSpell(String name, int requiredLevel, int experienceEarned) {
        super(name, SpellType.WIND, requiredLevel, experienceEarned);
    }
}
