package edu.upc.eetac.dsa.beeter;

import edu.upc.eetac.dsa.beeter.entity.BeeterRootAPI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

/**
 * Created by Jordi on 16/10/2015.
 *
 * http://eetacdsa0.upc.es/rest-book/hateoas/enlaces_del_recurso_beeterrootapi.html
 */
@Path("/")
public class BeeterRootAPIResource {
    @Context
    private SecurityContext securityContext;

    private String userid;

    @GET
    @Produces(BeeterMediaType.BEETER_ROOT)
    public BeeterRootAPI getRootAPI() {
        if(securityContext.getUserPrincipal()!=null)
            userid = securityContext.getUserPrincipal().getName();
        BeeterRootAPI beeterRootAPI = new BeeterRootAPI();

        return beeterRootAPI;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}