package edu.upc.eetac.dsa.beeter;

import edu.upc.eetac.dsa.beeter.entity.BeeterError;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.Response;

/**
 * Created by Jordi on 07/10/2015.
 *
 * JAX-RS define una jerarquía de excepciones que tienen su ancestro común en la clase WebApplicationException.
 * Instancias de esta clase ya las hemos utilizado para devolver errores HTTP 401 - Unauthorized
 * en el filtro de autorización que implementamos.

 Jersey proporciona un método para mapear excepciones a respuestas específicas como, por ejemplo, una que incluya
 como entidad de la respuesta un error en formato JSON. Recordad que en el capítulo del modelo de datos ya consideramos
 una entidad para tal efecto. Crearemos en el paquete edu.upc.eetac.dsa.beeter una clase anotada
 con @Provider que implementa la interfaz ExceptionMapper<WebApplicationException> y de nombre WebApplicationExceptionMapper:

 La implementación del método toResponse() crea una nueva entidad BeeterError que incluye en la respuesta HTTP con estado de error

 Con este mapeo tratamos todas las excepciones que heredan de WebApplicationException.
 */


@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {
    @Override
    public Response toResponse(WebApplicationException e) {
        BeeterError error = new BeeterError(e.getResponse().getStatus(), e.getMessage());
        return Response.status(error.getStatus()).entity(error).type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}