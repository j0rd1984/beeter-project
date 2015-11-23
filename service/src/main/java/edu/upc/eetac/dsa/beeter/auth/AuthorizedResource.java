package edu.upc.eetac.dsa.beeter.auth;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Jordi on 07/10/2015.
 *
 * La idea es tener una clase implementada como un Singleton a la que podamos interrogar sobre si un URI con un método
 * determinado necesita autorización o no. En primer lugar implementaremos en el paquete edu.upc.eetac.dsa.beeter.auth
 * la clase AuthorizedResource que modela el objeto JSON que contiene el array JSON definido en el fichero
 */
public class AuthorizedResource {
    private String path;
    private List<String> methods;
    private Pattern pattern;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
        pattern = Pattern.compile(path);
    }

    public List<String> getMethods() {
        return methods;
    }

    public void setMethods(List<String> methods) {
        this.methods = methods;
    }

    public Pattern getPattern() {
        return pattern;
    }
}
