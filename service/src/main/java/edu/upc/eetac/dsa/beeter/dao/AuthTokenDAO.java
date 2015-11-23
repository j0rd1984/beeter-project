package edu.upc.eetac.dsa.beeter.dao;

import edu.upc.eetac.dsa.beeter.auth.UserInfo;
import edu.upc.eetac.dsa.beeter.entity.AuthToken;

import java.sql.SQLException;

/**
 * Created by Jordi on 05/10/2015.
 */
/*
En primer lugar creamos en el paquete edu.upc.eetac.dsa.beeter.dao la interfaz DAO AuthTokenDAO con tres métodos
que nos permiten obtener el usuario que tiene asignado el token, crear el token para un usuario y,
por último, eliminar el token asociado a un usuario:
 */
public interface AuthTokenDAO {
    public UserInfo getUserByAuthToken(String token) throws SQLException;
    public AuthToken createAuthToken(String userid) throws SQLException;
    public void deleteToken(String userid) throws  SQLException;
}