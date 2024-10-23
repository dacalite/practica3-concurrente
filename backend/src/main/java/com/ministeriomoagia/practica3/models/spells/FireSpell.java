package com.ministeriomoagia.practica3.models.spells;

import com.ministeriomoagia.practica3.models.Spell;
import com.ministeriomoagia.practica3.models.SpellType;

public class FireSpell extends Spell {

    public FireSpell(String name, int requiredLevel, int experienceEarned) {
        super(name, SpellType.FIRE, requiredLevel, experienceEarned);
    }
}
