package edu.upc.eetac.dsa.beeter;

/**
 * Created by Jordi on 07/10/2015.
 * os diferentes tipos de contenido (media type) los declararemos como constante en una interfaz denominada BeeterMediaType
 * que crearemos en el paquete edu.upc.eetac.dsa.beeter:
 *
 * Todos estos tipos se declaran según lo que diseñamos en la sección del contrato uniforme.
 */


public interface BeeterMediaType {
    public final static String BEETER_AUTH_TOKEN = "application/vnd.dsa.beeter.auth-token+json";
    public final static String BEETER_USER = "application/vnd.dsa.beeter.user+json";
    public final static String BEETER_STING = "application/vnd.dsa.beeter.sting+json";
    public final static String BEETER_STING_COLLECTION = "application/vnd.dsa.beeter.sting.collection+json";
    public final static String BEETER_ROOT = "application/vnd.dsa.beeter.root+json";
}