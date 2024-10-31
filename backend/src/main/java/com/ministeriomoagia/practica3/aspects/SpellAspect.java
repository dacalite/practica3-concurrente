package com.ministeriomoagia.practica3.aspects;

import com.ministeriomoagia.practica3.exceptions.InsufficientLevelException;
import com.ministeriomoagia.practica3.exceptions.SpellTypeMismatchException;
import com.ministeriomoagia.practica3.models.Magician;
import com.ministeriomoagia.practica3.models.Spell;
import com.ministeriomoagia.practica3.services.LoggingService;
import com.ministeriomoagia.practica3.services.MagicianService;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpellAspect implements MethodInterceptor {

    private final LoggingService loggingService;
    private final MagicianService magicianService;

    @Autowired
    public SpellAspect(LoggingService loggingService, MagicianService magicianService) {
        this.loggingService = loggingService;
        this.magicianService = magicianService;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Magician magician = (Magician) invocation.getThis();
        Spell spell = (Spell) invocation.getArguments()[0]; // Obtenemos el hechizo

        // Validación del tipo
        assert magician != null;
        if (!magician.getType().equals(spell.getType())) {
            loggingService.logError(String.format("Magician %s [%s] cannot evoke %s spell due to type incompatibility", magician.getName(), magician.getType(), spell.getName()));
            throw new SpellTypeMismatchException("Type mismatch");
        }

        // Validación del nivel
        if (magician.getLevel() < spell.getRequiredLevel()) {
            loggingService.logError(String.format("Magician %s [Lvl. %d] does not have the required level to evoke %s spell", magician.getName(), magician.getLevel(), spell.getName()));
            throw new InsufficientLevelException("Insufficient level");
        }

        // Acción antes de invocar el método
        System.out.printf("%s is preparing to evoke %s\n", magician.getName(), spell.getName());

        // Continuar con la invocación real del método
        Object result = invocation.proceed();

        // Acción después de invocar el método
        System.out.printf("%s successfully evoked %s\n", magician.getName(), spell.getName());

        // Registrar el evento
        loggingService.logEvent(String.format("Magician %s [Lvl. %d] evoked %s and earned %d XP", magician.getName(), magician.getLevel(), spell.getName(), spell.getExperienceEarned()));

        //Incrementar la experiencia del mago
        magicianService.increaseMagicianExperience(magician.getName(), spell.getExperienceEarned());

        return result;
    }
}
