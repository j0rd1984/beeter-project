package edu.upc.eetac.dsa.beeter.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import javax.ws.rs.container.ContainerRequestContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Jordi on 07/10/2015.
 *
 * Implementaremos la clase Singleton que permitirá determinar si un recurso necesita autorización o no.
 * En el mismo paquete creamos la clase Authorized:
 *
 * En el constructor de la clase se obtiene el fichero authorized.json y lo convertimos a una lista de instancias AuthorizedResource
 * utilizando Jackson. El filtro de autenticación utilizará el método isAuthorized() para comprobar si la petición necesita autorización
 * o no. Este método busca en la lista si el path pasado coincide con alguna de las expresiones regulares y además también coincide
 * con alguno de los métodos asociados.
 */
public class Authorized {
    private static Authorized instance;
    private List<AuthorizedResource> authorizedResourcesList;

    private Authorized() throws IOException {
        InputStream in = this.getClass().getClassLoader()
                .getResourceAsStream("authorized.json");
        ObjectMapper objectMapper = new ObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        authorizedResourcesList = objectMapper.readValue(in, typeFactory.constructCollectionType(List.class, AuthorizedResource.class));
    }

    public static Authorized getInstance() throws IOException {
        if (instance == null)
            instance = new Authorized();
        return instance;
    }

    public boolean isAuthorized(ContainerRequestContext requestContext) {
        String path = requestContext.getUriInfo().getPath();
        String method = requestContext.getMethod();
        if(method.equals("OPTIONS"))
            return true;
        if(path.isEmpty() && method.equals("GET") && requestContext.getHeaderString("X-Auth-Token") == null)
            return true;
        for(AuthorizedResource r : authorizedResourcesList){
            if(r.getPattern().matcher(path).matches() && r.getMethods().contains(method) )
                return true;
        }
        return false;
    }
}