package IGT.Customer;

import IGT.Flight.Flight;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;


@Entity
@Table(name = "Customers")
public class Customer {

    @Id
    @GeneratedValue
    @Column(name = "customer_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "belongsToCustomer", cascade = CascadeType.ALL)
    private List<Phone> phoneList = new ArrayList<Phone>();

    @ManyToMany(mappedBy = "customers")
    public Set<Flight> flights = new HashSet<Flight>();

    public Customer(String name) {
        setName(name);
    }

    public Customer() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Phone> getPhones() {
        return phoneList;
    }

    public void addPhone(Phone p) {
        if (!phoneList.contains(p)) {
            phoneList.add(p);
            if (p.belongsToCustomer != null) {
                p.belongsToCustomer.phoneList.remove(p);
            }
            p.setBelongsToCustomer(this);

        }
    }

    public JSONObject toJSON() {
        JSONObject customerJson = new JSONObject();
        try {
            customerJson.put("customerId", id);
            customerJson.put("name", getName());
            JSONArray phones = new JSONArray();
            phoneList.forEach(phone -> phones.put(phone.getPhoneNumber()));
            customerJson.put("phones", phones);
        } catch (Exception e) {
            System.out.println("cannot convert customer" + getName() + " to json");
            e.printStackTrace();
        }
        return customerJson;
    }
}