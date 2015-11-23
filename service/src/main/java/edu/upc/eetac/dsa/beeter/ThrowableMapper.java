package edu.upc.eetac.dsa.beeter;

import edu.upc.eetac.dsa.beeter.entity.BeeterError;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by Jordi on 07/10/2015.
 *
 *  Sin embargo, durante la ejecución se pueden ir produciendo otro tipo de excepciones que no heredan en su jerarquía de esta clase.
 *  Por ejemplo, bugs de código no contemplados y que lleven a un NullPointerException.
 *  En Java todas las excepciones tienen como primer ancestro, descontando java.lang.Object, a la clase java.lang.Throwable.
 *  Así pues, implementaremos otro mapeador para cualquier otra excepción que no sea WebApplicationException y que interpretaremos
 * como un error de servidor. Esta clase la crearemos en el paquete edu.upc.eetac.dsa.beeter anotándola con @Provider
 * y que implementa la interfaz ExceptionMapper<Throwable> y de nombre ThrowableMapper:
 *
 * Observad que todos los errores de este tipo se devuelven con código de estado HTTP 500 Internal Server Error.
 */
@Provider
public class ThrowableMapper implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable throwable) {
        throwable.printStackTrace();
        BeeterError error = new BeeterError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), throwable.getMessage());
        return Response.status(error.getStatus()).entity(error).type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}