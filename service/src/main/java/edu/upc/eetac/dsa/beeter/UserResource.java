package edu.upc.eetac.dsa.beeter;

import edu.upc.eetac.dsa.beeter.dao.AuthTokenDAOImpl;
import edu.upc.eetac.dsa.beeter.dao.UserAlreadyExistsException;
import edu.upc.eetac.dsa.beeter.dao.UserDAO;
import edu.upc.eetac.dsa.beeter.dao.UserDAOImpl;
import edu.upc.eetac.dsa.beeter.entity.AuthToken;
import edu.upc.eetac.dsa.beeter.entity.User;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

/**
 * Created by Jordi on 07/10/2015.
 *
 *Empezaremos a trabajar sobre el recurso usuario creando la clase recurso raíz UserResource dentro del paqueteedu.upc.eetac.dsa.beeter.
 * Una vez creada la anotaremos con @Path para declarar la URI relativa del recurso como users, es decir,
 * que la dirección del recurso será: http://[ip]:[port]/beeter/users (recordad que la URI base se define en la clase Main).
 *
 * En primer lugar implementaremos la operación de crear un usuario en un método de UserResource al que denominaremos registerUser().
 * Según el mapeo de las operaciones CRUD a métodos HTTP, a la operación Create le corresponde el método HTTP POST.
 * El recurso que se va a crear aún no se puede representar mediante un formato derivado de JSON puesto que el atributo de estado id
 * lo genera automáticamente el servicio. Así pues, los datos que necesitamos para crear un usuario (identificador de login,
 * contraseña y dirección de correo electrónico) los enviaremos utilizando el tipo de contenido application/x-www-form-urlencoded
 * que es el tipo de contenido por defecto que se utiliza para enviar formularios web. La entidad de la respuesta será un usuario
 * representado en formato application/vnd.spotgram.user+json. Si_todo va bien y el usuario se crea correctamente responderemos
 * con una respuesta HTTP con código de estado 201 - Created con cabecera Location con valor igual a la dirección del
 * recurso que se acaba de crear, esto es, http://[ip]:[port]/spotgrams/users/{id} siendo {id} igual al valor del identificador
 * de usuario que ha autogenerado el servicio. La entidad de la respuesta HTTP será un AuthenticationToken:
 *
 * # La anotación @POST indica que este método está designado para procesar las peticiones HTTP POST realizadas sobre la URI relativa
 * users (recordad que esto lo indicamos con la anotación @Path de la clase recurso.
 * # La anotación @Consumes indica que espera los parámetros pasados según el formato application/x-www-form-urlencoded que
 * es el valor que tiene la constante MediaType.APPLICATION_FORM_URLENCODED. La petición HTTP enviará los datos del formulario
 * según este formato y además deberá añadir a la petición la cabecera Content-Type con valor application/x-www-form-urlencoded.
 * Si no envía esta cabecera la petición será rechazada.
 * # La anotación @Produces indica que la respuesta estará en formato application/vnd.dsa.beeter.auth-token+json.
 * Si la petición HTTP no incluye cabecera HTTP Accept la petición será procesada (al no incluír esta cabecera se entiende
 * que el cliente no muestra preferencia por el formato de la entidad de la respuesta). Pero si incluye esta cabecera deberá
 * tener el valor application/vnd.dsa.beeter.auth-token+json para ser procesada.
 * # Los parámetros del método registerUser() anotados con @FormParam obtienen su valor al inyectar Jersey el valor del parámetro
 * de la petición de nombre igual al pasado en la anotación. Por ejemplo: @FormParam("password") String password
 * hace que el parámetro password tenga valor igual al valor de nombre password de los parámetros de la entidad de la petición pasados en formato application/x-www-form-urlencoded.
 * # El último parámetro del método de tipo UriInfo está anotado con @Context. Jersey inyectará este parámetro a partir del contexto y
 * lo utilizamos para obtener la ruta absoluta al recurso y, con ello, crear la dirección del nuevo recurso creado en la línea: URI uri = new URI(uriInfo.getAbsolutePath().toString() + "/" + user.getId());
 * # El método utiliza la clase concreta DAO UserDAOImpl para persistir los datos del nuevo usuario en la base de datos y AuthTokenDAOImpl para obtener el token de acceso.
 * En el caso de que ya haya un usuario creado se recoge la excepción UserAlreadyExistsException y se relanza como un error HTTP 409 Conflict.
 * # La respuesta se construye a partir de la clase Response de Jersey a la que sucesivamente se le va indicando:
        · el código de estado de la petición 201 - Created y con la cabecera HTTP Location igual a la dirección del nuevo recurso: created(uri).
        · el tipo de contenido de la respuesta, es decir, el valor de la cabecera Content-Type: type(BeeterMediaType.BEETER_AUTH_TOKEN)
        · la entidad de la respuesta: entity(authenticationToken)
        · y, por último, se llama al método build() para que se construya la respuesta HTTP que se le envía al cliente.
 *
 *
 * # El método está anotado con @GET lo que indica que este método está designado para procesar las peticiones HTTP GET realizadas sobre la URI relativa users/{id}.
   # El valor de la variable {id} se obtiene anotando un parámetro del método con la anotación @PathParam("id").
 En general, con el nombre de la variable como valorde la anotación @PathParam.
   # Para obtener el usuario cuyo identificador es el valor de la variable {id} utilizamos una instancia de la clase concreta del
 DAO de usuario. Recordad que este método devuelve null si no encuentra ningún usuario con ese identificador.
 En ese caso, lanzaremos una excepción NotFoundException, que generará una respuesta HTTP con estado 404 - Not Found.
   #En este caso el tipo de retorno del método es directamente una entidad User.
 En los casos en los que la respuesta tiene estado 200 - OK y no hay que añadir ningún campo en la cabecera de la respuesta,
 es suficiente con devolver la entidad y Jersey se encarga de construír la respuesta a través del módulo JSON que hayamos configurado.
 *
 *
 * http://eetacdsa0.upc.es/rest-book/implementacion_del_servicio/actualizacion_del_perfil_de_usuario.html
 *
 *
 */
@Path("users")

public class UserResource {
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(BeeterMediaType.BEETER_AUTH_TOKEN)
    public Response registerUser(@FormParam("loginid") String loginid, @FormParam("password") String password, @FormParam("email") String email, @FormParam("fullname") String fullname, @Context UriInfo uriInfo) throws URISyntaxException {
        if(loginid == null || password == null || email == null || fullname == null)
            throw new BadRequestException("all parameters are mandatory");
        UserDAO userDAO = new UserDAOImpl();
        User user = null;
        AuthToken authenticationToken = null;
        try{
            user = userDAO.createUser(loginid, password, email, fullname);
            authenticationToken = (new AuthTokenDAOImpl()).createAuthToken(user.getId());
        }catch (UserAlreadyExistsException e){
            throw new WebApplicationException("loginid already exists", Response.Status.CONFLICT);
        }catch(SQLException e){
            throw new InternalServerErrorException();
        }
        URI uri = new URI(uriInfo.getAbsolutePath().toString() + "/" + user.getId());
        return Response.created(uri).type(BeeterMediaType.BEETER_AUTH_TOKEN).entity(authenticationToken).build();
    }
    @Path("/{id}")
    @GET
    @Produces(BeeterMediaType.BEETER_USER)
    public User getUser(@PathParam("id") String id) {
        User user = null;
        try {
            user = (new UserDAOImpl()).getUserById(id);
        } catch (SQLException e) {
            throw new InternalServerErrorException(e.getMessage());
        }
        if(user == null)
            throw new NotFoundException("User with id = "+id+" doesn't exist");
        return user;
    }
    @Context
    private SecurityContext securityContext;
    @Path("/{id}")
    @PUT
    @Consumes(BeeterMediaType.BEETER_USER)
    @Produces(BeeterMediaType.BEETER_USER)
    public User updateUser(@PathParam("id") String id, User user) {
        if(user == null)
            throw new BadRequestException("entity is null");
        if(!id.equals(user.getId()))
            throw new BadRequestException("path parameter id and entity parameter id doesn't match");
        String userid = securityContext.getUserPrincipal().getName();
        if(!userid.equals(id))
            throw new ForbiddenException("operation not allowed");

        UserDAO userDAO = new UserDAOImpl();
        try {
            user = userDAO.updateProfile(userid, user.getEmail(), user.getFullname());
            if(user == null)
                throw new NotFoundException("User with id = "+id+" doesn't exist");
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        }
        return user;
    }
    @Path("/{id}")
    @DELETE
    public void deleteUser(@PathParam("id") String id){
        String userid = securityContext.getUserPrincipal().getName();
        if(!userid.equals(id))
            throw new ForbiddenException("operation not allowed");
        UserDAO userDAO = new UserDAOImpl();
        try {
            if(!userDAO.deleteUser(id))
                throw new NotFoundException("User with id = "+id+" doesn't exist");
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        }
    }
}
