package pl.polsl.kubala.patryk.web.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Main class of the servlet that demonstrates parameter download given during
 * servlet initialization
 *
 * @author Patryk Kubala
 * @version 1.0
 */
@Path("rest")
public class JakartaEE8Resource {
    
    @GET
    public Response ping(){
        return Response
                .ok("ping")
                .build();
    }
}
