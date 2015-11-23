package edu.upc.eetac.dsa.beeter.dao;

import edu.upc.eetac.dsa.beeter.entity.User;

import java.sql.SQLException;

/**
 * Created by Jordi on 05/10/2015.
 */
/*
Dentro del paquete edu.upc.eetac.dsa.beeter.dao que acabáis de crear implementaremos la interfaz DAO para el usuario.
Seleccionáis el paquete, Alt+Insert, Java Class y en el diálogo denomináis a la clase UserDAO y como Kind escogéis Interface.

 El método createUser() crea en la base de datos un usuario con rol registered.
 El método updateProfile() actualiza el perfil del usuario.
 El único cambio de perfil posible es la dirección de correo electrónico tal y como veremos en el capítulo de Implementación del servicio.
 El método getUserById() devuelve el usuario cuyo identificador es que se pasa como parámetro id al método.
 El método getUserByUsername() hace lo mismo pero el parámero de búsqueda es el identificador de login.
 El método deleteUser() elimina el usuario cuyo identificador se pasa como parámetro y el método checkPassword() comprueba si la contraseña que envía el usuario coincide con la que hay almacenada en la base de datos.
 */

public interface UserDAO {
    public User createUser(String loginid, String password, String email, String fullname) throws SQLException, UserAlreadyExistsException;

    public User updateProfile(String id, String email, String fullname) throws SQLException;

    public User getUserById(String id) throws SQLException;

    public User getUserByLoginid(String loginid) throws SQLException;

    public boolean deleteUser(String id) throws SQLException;

    public boolean checkPassword(String id, String password) throws SQLException;
}
