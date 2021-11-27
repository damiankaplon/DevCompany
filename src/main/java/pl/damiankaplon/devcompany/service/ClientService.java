package pl.damiankaplon.devcompany.service;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import pl.damiankaplon.devcompany.dbutil.DbUtil;
import pl.damiankaplon.devcompany.model.Client;
import pl.damiankaplon.devcompany.service.exceptions.NoClientsFound;
import pl.damiankaplon.devcompany.service.exceptions.NotSpecifiedAllArguments;
import pl.damiankaplon.devcompany.service.exceptions.PeselAlreadyInDb;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class ClientService {

    private final SessionFactory sessionFactory;

    public ClientService(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public void saveClient(Client client) throws PeselAlreadyInDb, NotSpecifiedAllArguments {
        if(client.getName().equals("") || client.getSurname().equals("") || client.getPesel().equals("")){
            throw new NotSpecifiedAllArguments();
        }
        Session session = this.sessionFactory.openSession();
        Query q = session.createQuery("from Client where pesel='"+client.getPesel()+"'");
        List<Client> clients = q.getResultList();
        Optional<Client> optionalClient = clients.stream().findAny();
        if(optionalClient.isPresent()) throw new PeselAlreadyInDb(session);
        session.beginTransaction();
        session.save(client);
        session.getTransaction().commit();
        session.close();
    }

    public List<Client> getClientByName(Client client) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        Query q = session.createQuery("from Client where name='"+client.getName()+"'");
        List<Client> clients = q.getResultList();
        session.getTransaction().commit();
        session.close();
        return clients;
    }

    public List<Client> getClientBySurname(Client client) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        Query q = session.createQuery("from Client where surname='"+client.getSurname()+"'");
        List<Client> clients = q.getResultList();
        session.getTransaction().commit();
        session.close();
        return clients;
    }

    public List<Client> getClientByPesel(Client client) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        Query q = session.createQuery("from Client where pesel='"+client.getPesel()+"'");
        List<Client> clients = q.getResultList();
        session.getTransaction().commit();
        session.close();
        return clients;
    }
    public List<Client> getClientByAllArgs(Client client) throws NoClientsFound {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        Query q = session.createQuery("from Client where name='"+client.getName()+"' and surname='"+client.getSurname()
        +"' or pesel='"+client.getPesel()+"'");
        List<Client> clients = q.getResultList();
        if(clients.isEmpty()) throw new NoClientsFound(session);
        session.getTransaction().commit();
        session.close();
        return clients;
    }

}
