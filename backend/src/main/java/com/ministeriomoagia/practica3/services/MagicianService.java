package com.ministeriomoagia.practica3.services;

import com.ministeriomoagia.practica3.models.Magician;
import com.ministeriomoagia.practica3.repositories.MagicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MagicianService {

    private final MagicianRepository magicianRepository;

    @Autowired
    public MagicianService(MagicianRepository magicianRepository) {
        this.magicianRepository = magicianRepository;
    }

    // Obtener todos los magos
    public List<Magician> getMagicians() {
        return magicianRepository.findAll();
    }

    // Obtener un mago por ID
    public Optional<Magician> getMagicianById(Long id) {
        return magicianRepository.findById(id);
    }

    // Obtener un mago por nombre
    public Optional<Magician> getMagicianByName(String name) {
        return magicianRepository.findMagicianByName(name);
    }

    // Agregar un nuevo mago
    public void addNewMagician(Magician magician) {
        Optional<Magician> magicianByName = magicianRepository.findMagicianByName(magician.getName());
        if (magicianByName.isPresent()) {
            throw new IllegalArgumentException("Name already exists");
        }
        magicianRepository.save(magician);
    }

    public void increaseMagicianExperience(String name, int expToIncrease) {
        Optional<Magician> magicianByName = magicianRepository.findMagicianByName(name);
        if (magicianByName.isEmpty()) {
            throw new IllegalArgumentException("Magician's name does not exist");
        }
        magicianByName.get().increaseExperience(expToIncrease);
        magicianRepository.save(magicianByName.get());
    }

    // Eliminar un mago por ID
    public void deleteMagician(Long id) {
        boolean exists = magicianRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Magician with id " + id + " doesn't exist.");
        }
        magicianRepository.deleteById(id);
    }
}
