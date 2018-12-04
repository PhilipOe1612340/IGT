package IGT.Server;

import IGT.Flight.Airport;
import IGT.Hibernate;
import org.codehaus.jettison.json.JSONArray;

import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/airport")
public class AirportApi {


    /**
     * <b>get all airports</b>
     * <p>
     * Path: /customers/all
     * Method: GET
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCustomers() {
        try {
            JSONArray response = new JSONArray();
            Hibernate.getInstance().<Airport>getTable("Airport").forEach(airport -> response.put(airport.toJSON()));
            return Responder.ok(response);
        } catch (Exception e){
            return Responder.exception(e);
        }
    }

    @OPTIONS
    @Path("/all")
    public Response optionsAll() {
        return Responder.preFlight();
    }

}
