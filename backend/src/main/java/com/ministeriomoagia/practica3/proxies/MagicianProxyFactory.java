package com.ministeriomoagia.practica3.proxies;

import com.ministeriomoagia.practica3.aspects.SpellAspect;
import org.springframework.aop.framework.ProxyFactory;
import com.ministeriomoagia.practica3.models.Magician;

public class MagicianProxyFactory {

    private final SpellAspect spellAspect;

    // Inyectamos el aspecto en el factory
    public MagicianProxyFactory(SpellAspect spellAspect) {
        this.spellAspect = spellAspect;
    }

    public Magician createMagicianProxy(Magician magician) {
        ProxyFactory proxyFactory = new ProxyFactory(magician);
        proxyFactory.addAdvice(spellAspect); // Añade el aspect como un MethodInterceptor
        return (Magician) proxyFactory.getProxy(); // Retorna el proxy
    }
}
