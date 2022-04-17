package com.IDPA.SolarApi;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;


/**
 * @author Malo Jaboulet
 * @version 1.0
 * @since 07.03.2022
 *
 * Diese Klasse definiert alle Services.
 */
@ApplicationPath("/data")
public class Config extends Application {

    /**
     * Definiert alle Service-Klassen
     * @return
     */
    @Override
    public Set<Class<?>> getClasses() {
        HashSet providers = new HashSet<Class<?>>();
        providers.add(SolarService.class);
        return providers;
    }

}