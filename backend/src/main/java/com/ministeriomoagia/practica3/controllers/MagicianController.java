package com.ministeriomoagia.practica3.controllers;

import com.ministeriomoagia.practica3.annotations.RequiresAuth;
import com.ministeriomoagia.practica3.aspects.SpellAspect;
import com.ministeriomoagia.practica3.dto.EvokeRequest;
import com.ministeriomoagia.practica3.exceptions.InsufficientLevelException;
import com.ministeriomoagia.practica3.exceptions.SpellTypeMismatchException;
import com.ministeriomoagia.practica3.models.Magician;
import com.ministeriomoagia.practica3.models.Spell;
import com.ministeriomoagia.practica3.models.SpellType;
import com.ministeriomoagia.practica3.models.spells.DarknessSpell;
import com.ministeriomoagia.practica3.models.spells.FireSpell;
import com.ministeriomoagia.practica3.models.spells.WaterSpell;
import com.ministeriomoagia.practica3.models.spells.WindSpell;
import com.ministeriomoagia.practica3.proxies.MagicianProxyFactory;
import com.ministeriomoagia.practica3.services.LoggingService;
import com.ministeriomoagia.practica3.services.MagicianService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders; // Import HttpHeaders
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("public/api/v1/magicians")
public class MagicianController {

    private final MagicianService magicianService;
    private final MagicianProxyFactory magicianProxyFactory;
    private final Map<SpellType, List<Spell>> spellMap = new HashMap<>();  // Mapa para almacenar hechizos
    private final LoggingService loggingService;

    @Autowired
    public MagicianController(MagicianService magicianService, SpellAspect spellAspect, LoggingService loggingService) {
        this.magicianService = magicianService;
        this.magicianProxyFactory = new MagicianProxyFactory(spellAspect); // Creamos el factory para proxies
        this.loggingService = loggingService;
    }

    // Inicialización del mapa con hechizos
    @PostConstruct
    public void init() {
        spellMap.put(SpellType.FIRE, Arrays.asList(new FireSpell("Incendio", 0, 200), new FireSpell("Inflamari", 1, 500), new FireSpell("Confringo", 5, 1000), new FireSpell("Fiendfyre", 15, 1500)));

        spellMap.put(SpellType.WATER, Arrays.asList(new WaterSpell("Aguamenti", 0, 200), new WaterSpell("Glacius", 1, 500), new WaterSpell("Aquarium", 5, 1000), new WaterSpell("Tsunamis", 15, 1500)));

        spellMap.put(SpellType.WIND, Arrays.asList(new WindSpell("Ventus", 0, 200), new WindSpell("Depulso", 1, 500), new WindSpell("Aero", 5, 1000), new WindSpell("Tempestus", 15, 1500)));

        spellMap.put(SpellType.DARKNESS, Arrays.asList(new DarknessSpell("Nox", 0, 200), new DarknessSpell("Obscuro", 1, 500), new DarknessSpell("Morsmordre", 5, 1000), new DarknessSpell("Nocturnis", 15, 1500)));
    }

    // Obtener todos los magos
    @RequiresAuth
    @GetMapping
    public List<Magician> getMagicians(@RequestHeader HttpHeaders headers) { // Accept HttpHeaders
        return magicianService.getMagicians();
    }

    // Obtener un mago por ID
    @RequiresAuth
    @GetMapping("/{id}")
    public Optional<Magician> getMagicianById(@PathVariable("id") Long id, @RequestHeader HttpHeaders headers) { // Accept HttpHeaders
        return magicianService.getMagicianById(id);
    }

    // Agregar un nuevo mago
    @RequiresAuth
    @PostMapping
    public ResponseEntity<String> addMagician(@RequestBody Magician magician, @RequestHeader HttpHeaders headers) { // Accept HttpHeaders
        try {
            magicianService.addNewMagician(new Magician(magician.getName(), magician.getExperience(), magician.getType()));
            loggingService.clearLogs();
            return ResponseEntity.ok("Magician created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Eliminar un mago por ID
    @RequiresAuth
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMagician(@PathVariable("id") Long id, @RequestHeader HttpHeaders headers) { // Accept HttpHeaders
        try {
            magicianService.deleteMagician(id);
            return ResponseEntity.ok("Magician deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Invocar hechizo
    @RequiresAuth
    @PostMapping("/evoke")
    public ResponseEntity<String> evokeSpell(@RequestBody EvokeRequest evokeRequest, @RequestHeader HttpHeaders headers) { // Accept HttpHeaders
        try {
            Optional<Magician> magicianOpt = magicianService.getMagicianByName(evokeRequest.getMagician());

            if (magicianOpt.isPresent()) {
                Magician magician = magicianOpt.get();
                Magician magicianProxy = magicianProxyFactory.createMagicianProxy(magician);

                // Obtener el hechizo basado en el nombre proporcionado
                Spell spellToEvoke = spellMap.values().stream().flatMap(List::stream).filter(spell -> spell.getName().equalsIgnoreCase(evokeRequest.getSpell())).findFirst().orElse(null);

                if (spellToEvoke == null) {
                    return ResponseEntity.badRequest().body("Spell not found");
                }

                try {
                    // Invocar el hechizo usando el proxy
                    magicianProxy.evoke(spellToEvoke);
                    return ResponseEntity.ok("Spell evoked successfully");
                } catch (SpellTypeMismatchException e) {
                    return ResponseEntity.badRequest().body("Type mismatch");
                } catch (InsufficientLevelException e) {
                    return ResponseEntity.badRequest().body("Insufficient level");
                } catch (Exception e) {
                    return ResponseEntity.badRequest().body("Error: Unable to evoke the spell.");
                }
            } else {
                return ResponseEntity.status(404).body("Magician not found");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
