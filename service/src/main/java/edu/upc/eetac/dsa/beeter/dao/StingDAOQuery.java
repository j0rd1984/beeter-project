package edu.upc.eetac.dsa.beeter.dao;

/**
 * Created by Jordi on 05/10/2015.
 *
 *
 * PAra "N" Stings http://eetacdsa0.upc.es/rest-book/paginacion/index.html
 *
 *
 *
/*
Observad que en la consulta GET_STINGS no se devuelve el valor del campo content.
Esto es así porque habitualmente en las colecciones no se devuelven todos los campos de los elementos sino sólo algunos que resulten significativos.
 */
public interface StingDAOQuery {
    public final static String UUID = "select REPLACE(UUID(),'-','')";
    public final static String CREATE_STING = "insert into stings (id, userid, subject, content) values (UNHEX(?), unhex(?), ?, ?)";
    public final static String GET_STING_BY_ID = "select hex(s.id) as id, hex(s.userid) as userid, s.content, s.subject, s.creation_timestamp, s.last_modified, u.fullname from stings s, users u where s.id=unhex(?) and u.id=s.userid";
   //TODOS!! public final static String GET_STINGS = "select hex(s.id) as id, hex(s.userid) as userid, s.subject, s.creation_timestamp, s.last_modified, u.fullname from stings s, users u where u.id=s.userid";
    // 5 en 5 public final static String GET_STINGS = "select hex(id) as id, hex(userid) as userid, subject, creation_timestamp, last_modified from stings order by creation_timestamp desc limit 5";
   public final static String GET_STINGS = "select hex(id) as id, hex(userid) as userid, subject, creation_timestamp, last_modified from stings where creation_timestamp < ? order by creation_timestamp desc limit 5";
    public final static String GET_STINGS_AFTER = "select hex(id) as id, hex(userid) as userid, subject, creation_timestamp, last_modified from stings  where creation_timestamp > ? order by creation_timestamp desc limit 5";
    public final static String UPDATE_STING = "update stings set subject=?, content=? where id=unhex(?) ";
    public final static String DELETE_STING = "delete from stings where id=unhex(?)";
}