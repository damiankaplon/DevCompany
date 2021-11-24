package pl.damiankaplon.devcompany.service;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import pl.damiankaplon.devcompany.model.Client;

import java.util.List;

public class ClientService {

    private SessionFactory sessionFactory;

    public ClientService(){
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    public void testSaveData(){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(new Client("Natalia", "Kaplon", "99010604291"));
        session.getTransaction().commit();
        session.close();
    }

}
