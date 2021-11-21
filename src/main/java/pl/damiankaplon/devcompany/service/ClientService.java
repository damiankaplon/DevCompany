package pl.damiankaplon.devcompany.service;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import pl.damiankaplon.devcompany.model.Client;

import java.util.List;

public class ClientService {

    private SessionFactory sessionFactory;

    public ClientService(){
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            this.sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch(Exception e){
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
