package IGT;

import IGT.Customer.Customer;
import IGT.Flight.PopularAirports;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.TransactionManager;
import java.rmi.ServerError;
import java.util.ArrayList;
import java.util.List;

public class Hibernate {

    private static final Hibernate instance = new Hibernate();
    private static TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);

    public static Hibernate getInstance() {
        return instance;
    }

    public synchronized void initFlightManagement(){
        try {
            PopularAirports.generate();
        } catch (Exception e){
            System.out.println("initFlightManagement failed");
            e.printStackTrace();
        }
    }

    public synchronized <T extends IClassID> void save(T object) throws ServerError {
        try {
            tm.begin();
            EntityManager em = emf.createEntityManager();
            if (object.getId() == null) {
                // save
                em.persist(object);
            } else {
                // update
                Object entry = em.find(getClass(object), ((T) object).getId());
                entry = object;

                em.merge(entry);
            }

            em.flush();
            em.close();
            tm.commit();
        } catch (Exception e) {
            throw new ServerError("failed to store: " + object.toJSON().toString(), new Error());
        } finally {
            emf.close();
        }
    }


    public synchronized <T extends IClassID> void delete(T object) throws ServerError {
        try {
            tm.begin();
            EntityManager em = emf.createEntityManager();
            em.remove(em.find(getClass(object), object.getId()));
            em.flush();
            em.close();
            tm.commit();
        } catch (Exception e) {
            throw new ServerError("failed to delete: " + object.toJSON().toString(), new Error());
        } finally {
            emf.close();
        }
    }

    public synchronized <T> List<T> getTable(String table) throws ServerError {
        List<T> customers = new ArrayList<>();
        try {
            tm.begin();
            EntityManager em = emf.createEntityManager();

            for (Object c : em.createQuery("FROM " + table).getResultList()) {
                customers.add((T) c);
            }
            em.flush();
            em.close();
            tm.commit();
        } catch (Exception e) {
            throw new ServerError("failed to get Table: " + table, new Error());
        } finally {
            emf.close();
        }
        return customers;
    }

    public synchronized <T extends IClassID> T getElementById(Object id, String table) throws ServerError {
        List<T> t = getTable(table);
        for (T c : t) {
            if (id == c.getId()) {
                return c;
            }
        }
        System.out.println("cant find element");
        return null;
    }

    private synchronized Class getClass(IClassID classID) {
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

