package IGT;

public class Config {

    public static final String PERSISTENCE_UNIT_NAME = PERSISTENCE_UNITS.OGM_MYSQL.name();

    public enum PERSISTENCE_UNITS {
        OGM_MYSQL, OGM_POSTGRESQL, OGM_MONGODB, OGM_NEO4J, OGM_INFINISPAN, OGM_CASSANDRA, OGM_REDIS,
    }
}
