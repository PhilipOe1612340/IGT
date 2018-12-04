package IGT.Flight;

import IGT.Customer.Customer;
import IGT.IClassID;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Flights")
public class Flight implements IClassID {

    @Id
    @GeneratedValue
    @Column(name = "flight_id")
    private Long id;

    private Date startTime;

    private long duration;

    private int miles;

    private int seatsEconomyClass;

    private int seatsFistClass;

    private PlaneType plane;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "FlightBookings",
            joinColumns = {@JoinColumn(name = "flight_id")},
            inverseJoinColumns = {@JoinColumn(name = "customer_id")}
    )
    public Set<Customer> customers = new HashSet<>();

    @OneToMany(mappedBy = "belongsToFlight", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FlightSegment> flightSegmentList = new ArrayList<>();

    public Flight() {
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getDuration() {
        return duration;
    }

    public Date getArrivalTime() {
        Date arrival = (Date) startTime.clone();
        arrival.setTime(startTime.getTime() + duration);
        System.out.println(startTime.toString() + "+" + duration + "=" + arrival.toString());
        return arrival;
    }

    public int getSeatsEconomyClass() {
        return seatsEconomyClass;
    }

    public void setSeatsEconomyClass(int seatsEconomyClass) {
        this.seatsEconomyClass = seatsEconomyClass;
    }

    public int getSeatsFistClass() {
        return seatsFistClass;
    }

    public void setSeatsFistClass(int seatsFistClass) {
        this.seatsFistClass = seatsFistClass;
    }

    public PlaneType getPlane() {
        return plane;
    }

    public void setPlane(PlaneType plane) {
        this.plane = plane;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public void setMiles(int miles) {
        this.miles = miles;
    }

    public int getMiles() {
        return miles;
    }

    public List<FlightSegment> getFlightSegmentList() {
        return flightSegmentList;
    }

    public void setFlightSegmentList(List<FlightSegment> flightSegmentList) {
        this.flightSegmentList = flightSegmentList;
    }

    public void addFlightSegment(FlightSegment p) {
        if (!this.flightSegmentList.contains(p)) {
            this.flightSegmentList.add(p);
            p.setBelongsToFlight(this);
        }
    }

    @Override
    public JSONObject toJSON() {
        JSONObject flight = new JSONObject();
        try {
            flight.put("flightId", this.getId());
            flight.put("startTime", this.getStartTime());
            flight.put("arrivalTime", this.getArrivalTime());
            flight.put("miles", this.getMiles());
            List<Long> airportsList = new ArrayList<>();
            for (int i = 0; i < this.getFlightSegmentList().size(); i++) {
                airportsList.add(this.getFlightSegmentList().get(i).getStart().getId());
                if (i == this.getFlightSegmentList().size() - 1) {
                    airportsList.add(this.getFlightSegmentList().get(i).getGoal().getId());
                }
            }
            flight.put("airportsList", new JSONArray(airportsList));
            System.out.println(flight.getJSONArray("airportsList"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return flight;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public String getClassId() {
        return "Flight";
    }
}