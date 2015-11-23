package edu.upc.eetac.dsa.beeter.dao;

import edu.upc.eetac.dsa.beeter.entity.Sting;
import edu.upc.eetac.dsa.beeter.entity.StingCollection;

import java.sql.SQLException;

/**
 * Created by Jordi on 05/10/2015.
 */

/*
Dentro del paquete edu.upc.eetac.dsa.beeter.dao implementad la interfaz DAO para los stings.
Ponedle como nombre StingDAO declarando los métodos necesarios para implementar las
operaciones de persistencia que definimos en el contrato uniforme del recurso Sting.

http://eetacdsa0.upc.es/rest-book/paginacion/index.html (Páginación)
 */

public interface StingDAO {
    public Sting createSting(String userid, String subject, String content) throws SQLException;
    public Sting getStingById(String id) throws SQLException;
    public StingCollection getStings(long timestamp, boolean before) throws SQLException;
    public Sting updateSting(String id, String subject, String content) throws SQLException;
    public boolean deleteSting(String id) throws SQLException;
}