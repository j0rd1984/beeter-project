package edu.upc.eetac.dsa.beeter;

import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

/**
 * Created by Jordi on 30/09/2015.
 *
 * Si el filtro lo hubiéramos implementado en el paquete edu.upc.eetac.dsa.beeter ya sería suficiente puesto que ya
 * configuramos vía ResourceConfig a este paquete como paquete donde Jersey tenía que buscar las clases "de interés".
 * Sin embargo tenemos que volver a la clase eeterResourceConfig` y registrar este paquete en la configuración de Jersey:
 *
 * Por último, para que dentro del servicio podamos discernir el procesado o no de peticiones en función del rol
 * que tenga el usuario tenemos que registrar RolesAllowedDynamicFeature como un proveedor en la configuración del ResourceConfig
 * añadiendo en el constructor de la clase BeeterResourceConfig la línea: register(RolesAllowedDynamicFeature.class);
 */

public class BeeterResourceConfig extends ResourceConfig {
    public BeeterResourceConfig() {
        register(RolesAllowedDynamicFeature.class);
        register(DeclarativeLinkingFeature.class);
        packages("edu.upc.eetac.dsa.beeter");
        packages("edu.upc.eetac.dsa.beeter.auth");
        packages("edu.upc.eetac.dsa.beeter.cors");
    }
}