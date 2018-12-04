package IGT.Server;

import IGT.Customer.Customer;
import IGT.Flight.Flight;
import IGT.Hibernate;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/booking")
public class BookingApi {
    /**
     * <b>Create new customer</b>
     * <p>
     * Path: /booking/new
     * Method: POST
     */
    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postNewBooking(String json) {
        try {
            JSONObject object = new JSONObject(json);
            if (!object.has("customerId") || !object.has("flightId")) {
                return Responder.badRequest();
            }
            Customer c = Hibernate.getInstance().getElementById(object.getInt("customerId"), "Customer");
            Flight f = Hibernate.getInstance().getElementById(object.getInt("flightId"), "Flight");

            //Long bookingId = Helper.bookFlight(c, f);
            JSONObject response = new JSONObject();
            response.put("bookingId", 5);
            return Responder.created(response);
        } catch (Exception e) {
            return Responder.exception(e);
        }
    }

    @OPTIONS
    @Path("/new")
    public Response optionsNew() {
        return Responder.preFlight();
    }
}
