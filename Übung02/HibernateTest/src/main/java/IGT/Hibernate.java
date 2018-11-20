package IGT;

import IGT.Customer.Customer;
import IGT.Customer.Phone;
import IGT.Flight.Airport;
import IGT.Flight.Flight;
import IGT.Flight.FlightSegment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class Hibernate {

    private static final Hibernate instance = new Hibernate();
    static Configuration conf;
    static SessionFactory sf;

    private Hibernate() {
        conf = new Configuration().configure()
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Flight.class)
                .addAnnotatedClass(FlightSegment.class)
                .addAnnotatedClass(Airport.class)
                .addAnnotatedClass(Phone.class);
        sf = conf.buildSessionFactory();
    }

    public static Hibernate getInstance() {
        return instance;
    }

    public <T extends IClassID> void save(T object) {
        try {
            Session session = sf.openSession();
            Transaction tx = session.beginTransaction();
            if (object.getId() == null) {
                // save
                session.save(object);
            } else {
                // update
                Object entry = session.find(getClass(object), ((T) object).getId());
                System.out.println(entry.toString());
                entry = object;

                session.merge(entry);
            }

            // session.flush();
            // session.close();
            tx.commit();
        } catch (Exception e) {
            System.err.println("inserting failed");
            e.printStackTrace();
        }
    }


    public <T extends IClassID> void delete(T object) {
        try {
            Session session = sf.openSession();
            Transaction tx = session.beginTransaction();
            session.remove(session.find(getClass(object), ((T) object).getId()));
            // session.flush();
            // session.close();
            tx.commit();
        } catch (Exception e) {
            System.err.println("updating failed");
            e.printStackTrace();
        }
    }

    public <T> List<T> getTable(String table) {
        System.out.println(table);
        List<T> customers = new ArrayList<>();
        try {
            Session session = sf.openSession();
            Transaction tx = session.beginTransaction();

            for (Object c : session.createQuery("FROM " + table).list()) {
                customers.add((T) c);
            }
            // session.flush();
            tx.commit();
        } catch (Exception e) {
            System.err.println("reading " + table + " failed");
            e.printStackTrace();
        }
        return customers;
    }

    private Class getClass(IClassID classID) {
        switch (classID.getClassId()) {
            case "Customer":
                return Customer.class;
            case "Phone":
                return Customer.class;
            case "Airport":
                return Customer.class;
            case "Flight":
                return Customer.class;
            case "FlightSegment":
                return Customer.class;
        }
        return null;
    }

}

