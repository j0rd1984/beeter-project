package edu.upc.eetac.dsa.beeter.dao;

/**
 * Created by Jordi on 05/10/2015.
 */
/*Cuando creemos un usuario tendremos que lanzar un error cuando el identificador de login pasado ya existe en la base de datos
(recordad que el identificador de login es único). Para ello crearemos una clase Exception que lanzaremos cuando no se pueda crear el usuario.
 En el paquete dao cread la excepción UserAlreadyExistsException:
*/
public class UserAlreadyExistsException extends Exception {
}