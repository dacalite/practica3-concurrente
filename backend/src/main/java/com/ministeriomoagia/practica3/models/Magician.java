package com.ministeriomoagia.practica3.models;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "magicians")
public class Magician {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )

    private long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private SpellType type;

    private int experience;

    public Magician() {
    }

    public Magician(String name, int initialExperience, SpellType type) {
        this.name = name;
        this.experience = initialExperience;
        this.type = type;
    }

    public int getLevel() {
        return (int) Math.floor((double) this.getExperience() / 1000);
    }

    public void increaseExperience(int expToIncrease) {
        experience += expToIncrease;
    }

    public void evoke(Spell spell) {
        System.out.printf("Magician %s is currently using %s\n", this.getName(), spell.getName());
    }
}
