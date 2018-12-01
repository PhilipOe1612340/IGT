package IGT.Server;

import IGT.Flight.Airport;
import IGT.Flight.Flight;
import IGT.Flight.FlightSegment;
import IGT.Hibernate;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Path("/flight")
public class FlightApi {

    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createFlight(String json) {
        // DateFormat df = new SimpleDateFormat("EEE MMM dd yyyy kk:mm:ss 'GMT'Z");
        Date start, arrival;
        JSONArray airportsList;
        int miles;
        try {
            JSONObject object = new JSONObject(json);
            start = new Date(); //df.parse(object.getString("startTime"));
            arrival = new Date(); //df.parse(object.getString("arrivalTime"));
            airportsList = object.getJSONArray("airportsList");
            miles = object.getInt("miles");
            if (miles < 1 || airportsList.length() < 2) {
                return Responder.badRequest();
            }
            Flight newFlight = new Flight();
            for (int i = 0; i < airportsList.length() - 1; i++) {
                Long currentId = airportsList.getLong(i);
                Long nextId = airportsList.getLong(i + 1);
                Airport currentAirport = Hibernate.getInstance().getElementById(currentId, "Airport");
                Airport nextAirport = Hibernate.getInstance().getElementById(nextId, "Airport");
                FlightSegment nextSegment = new FlightSegment(newFlight, currentAirport, nextAirport);
                newFlight.addFlightSegment(nextSegment);
            }
            newFlight.setStartTime(start);
            newFlight.setArrivalTime(arrival);
            newFlight.setMiles(miles);
            Hibernate.getInstance().save(newFlight);
            return Responder.created(newFlight.toJSON());
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
