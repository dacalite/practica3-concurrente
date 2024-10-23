package com.ministeriomoagia.practica3.models;

import lombok.Getter;

@Getter
public abstract class Spell {
    private final String name;
    private final SpellType type;
    private final int requiredLevel;
    private final int experienceEarned;

    public Spell(String name, SpellType type, int requiredLevel, int experienceEarned) {
        this.name = name;
        this.type = type;
        this.requiredLevel = requiredLevel;
        this.experienceEarned = experienceEarned;
    }
}
