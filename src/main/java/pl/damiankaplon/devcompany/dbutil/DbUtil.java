package pl.damiankaplon.devcompany.dbutil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DbUtil {

    public static SessionFactory sessionFactory;
    private static final Logger logger = LogManager.getLogger();

    public static void initialize() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
            logger.info("Hibernate successfully configured!");
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had troubles building the SessionFactory
            // so destroy it manually.
            logger.error("Hibernate is not configured. Exception occurred!");
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }
}
