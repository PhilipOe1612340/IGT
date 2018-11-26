package IGT.Server;

import IGT.Customer.Customer;
import IGT.Customer.Phone;
import IGT.Hibernate;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customer")
public class CustomerApi {

    /**
     * <b>get all customers</b>
     *
     * Path: /customers/all
     * Method: GET
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCustomers() {
        JSONArray response = new JSONArray();
        Hibernate.getInstance().<Customer>getTable("Customer").forEach(customer -> response.put(customer.toJSON()));
        return Responder.ok(response);
    }

    /**
     * <b>Create new customer</b>
     *
     * Path: /customers/new
     * Method: POST
     */
    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postNewCustomer(String json) {
        try {
            JSONObject object = new JSONObject(json);
            if (object.getString("name") != null && object.getJSONArray("phones") != null) {
                Customer newCustomer = new Customer();
                newCustomer.setName(object.getString("name"));
                JSONArray phones = object.getJSONArray("phones");
                for (int i = 0; i < phones.length(); i++) {
                    newCustomer.addPhone(new Phone((String) phones.get(i)));
                }
                Hibernate.getInstance().save(newCustomer);
                return Responder.created(newCustomer.toJSON());
            }
            return Responder.badRequest();
        } catch (Exception e) {
            return Responder.exception(e);
        }
    }

    /**
     * <b>Get customer by id</b>
     *
     * Path: /customers/{id}
     * Method: GET
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOneCustomer(@PathParam("id") String id) {
        try {
            for (Customer c : Hibernate.getInstance().<Customer>getTable("Customer")) {
                if (c.getId() == Long.parseLong(id)) {
                    return Responder.ok(c.toJSON());
                }
            }
            return Responder.badRequest();
        } catch (Exception e) {
            return Responder.exception(e);
        }
    }

    /**
     * <b>Update customer by id</b>
     *
     * Path: /customers/{id}
     * Method: POST
     */
    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomer(String json, @PathParam("id") String id) {
        try {
            JSONObject object = new JSONObject(json);
            for (Customer c : Hibernate.getInstance().<Customer>getTable("Customer")) {
                if (c.getId() == Long.parseLong(id)) {
                    if (object.has("name")) {
                        c.setName(object.getString("name"));
                    }
                    if (object.has("phones")) {
                        c.removeAllPhones();
                        JSONArray phones = object.getJSONArray("phones");
                        for (int i = 0; i < phones.length(); i++) {
                            Phone p = new Phone((String) phones.get(i));
                            c.addPhone(p);
                            Hibernate.getInstance().save(p);
                        }
                    }
                    Hibernate.getInstance().save(c);
                    return Responder.ok(c.toJSON());
                }
            }
            return Responder.badRequest();
        } catch (Exception e) {
            return Responder.exception(e);
        }
    }

    /**
     * <b>Delete customer by id</b>
     *
     * Path: /customers/{id}
     * Method: DELETE
     */
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCustomer(@PathParam("id") String id) {
        try {
            for (Customer c : Hibernate.getInstance().<Customer>getTable("Customer")) {
                if (c.getId() == Long.parseLong(id)) {
                    c.removeAllPhones();
                    Hibernate.getInstance().delete(c);
                    return Responder.ok(new JSONObject("{\"message\":\"done\"}"));
                }
            }
            return Responder.badRequest();
        } catch (Exception e) {
            return Responder.exception(e);
        }
    }

    @OPTIONS
	@Path("/all")
	public Response optionsAll() {
		return Responder.preFlight();
    }
    
    @OPTIONS
	@Path("/new")
	public Response optionsNew() {
		return Responder.preFlight();
    }
    
    @OPTIONS
	@Path("/{id}")
	public Response optionsId() {
		return Responder.preFlight();
	}
}

