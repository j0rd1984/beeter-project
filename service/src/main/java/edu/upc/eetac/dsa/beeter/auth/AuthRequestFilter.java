package edu.upc.eetac.dsa.beeter.auth;

import edu.upc.eetac.dsa.beeter.dao.AuthTokenDAOImpl;
import edu.upc.eetac.dsa.beeter.entity.Role;

import javax.annotation.Priority;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Jordi on 07/10/2015.
 */

/*

Una clase que implemente la interfaz ContainerResponseFilter implementará un filtro que aplicará a todas las respuestas,
mientras que una clase que implemente la interfaz ContainerRequestFilter un filtro que aplica a todas las peticiones.
Los filtros de petición se ejecutan antes de que se procese la petición por el recurso, por lo tanto,
si queremos comprobar si un usuario está autenticado correctamente y, además, tiene privilegios para invocar la petición,
habrá que hacerlo en un filtro de petición.

En el paquete edu.upc.eetac.dsa.beeter.auth que creamos en el capítulo anterior cread la clase AuthRequestFilter.
Una vez creada la haremos implementar la interfaz ContainerRequestFilter e implementaremos el método filter() que declara la interfaz:

 Jersey tiene que ser consciente de dónde tiene que ir a buscar los filtros. Ahora tenemos que hacer dos cosas,
 por un lado indicarle a Jersey que esta clase que estamos implementando, es decir, el filtro, es "interesante" anotándola con @Provider.
 Además, para asegurar que el filtro de autenticación se ejecuta tan pronto como es posible hay que asignarle una prioridad AUTHENTICATION,
 cuyo valor se declara como constante en la clase Priorities, a través de la anotación @Priority.

Como método de autenticación utilizaremos la autenticación basada en tokens.
El usuario inicialmente realiza un login mediante el cual obtiene un token de acceso que es válido hasta que el usario haga logout.
 El token se envía utilizando la cabecera HTTP extendida X-Auth-Token.
 Comenzamos la implementación del método filter() haciendo que obtenga el valor de la cabecera X-Auth-Token y en caso de que
 no esté presente lance una excepción WebApplicationException con estado HTTP 401 - Unauthorized indicando que es obligatorio
 que el usuario envíe esta cabecera en las peticiones que necesitan autorización.

Una vez obtenido el token tenemos que comprobar si es un token válido y en ese caso recuperar la información del usuario asociada a dicho token.
La información de seguridad que concierne a una petición se puede obtener mediante la inyección de una instancia de SecurityContext utilizando la anotación @Context.
El SecurityContext puede obtenerse directamente de la instancia de ContainerRequestContext que se pasa como parámetro al método filter() vía el método getSecurityContext().
También se puede reemplazar el SecurityContext por defecto con uno hecho a medida utilizando el método setSecurityContext(SecurityContext).
Al código que ya tenemos en el método filter() anañadimos al principio del método la definición de una variable booleana:

En el código anterior primero se obtiene la información relativa a la seguridad del usuario a través de la clase concreta DAO AuthTokenDAOImpl que implementamos anteriormente.
La variable booleana secure es cierta si la petición se realiza vía HTTPS y falsa en caso contrario.
Esta variable se define porque la implementación a medida de un SecurityContext obliga a devolver un booleano que indique si la petición es segura o no.
Esto es útil cuando en el código de nuestro servicio queramos impedir que se procesen peticiones que no hayan sido hechas vía HTTPS.
Cualquier excepción que se produzca en el acceso a la base de datos se relanza en forma de InternalServerErrorException que es una excepción que proporciona Jersey para devolver errores HTTP 500. En la línea:


En el código anterior primero se obtiene la información relativa a la seguridad del usuario a través de la clase concreta DAO AuthTokenDAOImpl que implementamos anteriormente.
La variable booleana secure es cierta si la petición se realiza vía HTTPS y falsa en caso contrario.
Esta variable se define porque la implementación a medida de un SecurityContext obliga a devolver un booleano que indique si la petición es segura o no. Esto es útil cuando en el código de nuestro servicio queramos impedir que se procesen peticiones que no hayan sido hechas vía HTTPS.
Cualquier excepción que se produzca en el acceso a la base de datos se relanza en forma de InternalServerErrorException que es una excepción que proporciona Jersey para devolver errores HTTP 500.
En la línea: requestContext.setSecurityContext(new SecurityContext() {...});

Resumiendo, con el filtro que acabamos de implementar conseguimos:

1.- Rechazar peticiones que no vengan con información de autenticación con error 401 - Unauthorized.
2.- Hacer accesible al servicio a través de un SecurityContext:
    # el identificador de usuario autenticado que realiza la petición.
    # el/los role/s que tiene asociado/s.
    # la seguridad del canal utilizado para transportar la petición.
    # el tipo de esquema de autenticación utilizado.

    Observando la salida vemos que el fallo se produce porque estamos recibiendo un error HTTP 401 Unauthorized.
     Esto se debe a que no estamos enviando ningún token válido y, por lo tanto, el filtro de autenticación rechaza el procesado de la petición.
     En general, habrá peticiones que no necesitann que se envíe información de autenticación.
     En nuestro servicio, por ejemplo, consultar los stings son peticiones que no necesitan estar autenticadas, por lo tanto,
     necesitamos indicar de alguna manera en el filtro que ciertas URI pasan el filtro de autenticación sin necesidad de autenticación de usuario.
     Comenzaremos añadiendo una excepción para peticiones HTTP GET sobre la URI myresource, añdiendo como primera línea del método filter():
 */

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthRequestFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if(Authorized.getInstance().isAuthorized(requestContext))
            return;

        final boolean secure = requestContext.getUriInfo().getAbsolutePath().getScheme().equals("https");

        String token = requestContext.getHeaderString("X-Auth-Token");
        if (token == null)
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        try {

            final UserInfo principal = (new AuthTokenDAOImpl()).getUserByAuthToken(token);
            if(principal==null)
                throw new WebApplicationException("auth token doesn't exists", Response.Status.UNAUTHORIZED);
            requestContext.setSecurityContext(new SecurityContext() {
                @Override
                public Principal getUserPrincipal() {
                    return principal;
                }

                @Override
                public boolean isUserInRole(String s) {
                    List<Role> roles = null;
                    if (principal != null) roles = principal.getRoles();
                    return (roles.size() > 0 && roles.contains(Role.valueOf(s)));
                }

                @Override
                public boolean isSecure() {
                    return secure;
                }

                @Override
                public String getAuthenticationScheme() {
                    return "X-Auth-Token";
                }
            });
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        }

    }
}