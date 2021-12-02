package pl.damiankaplon.devcompany.service;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.damiankaplon.devcompany.model.Client;
import pl.damiankaplon.devcompany.service.exception.ManySamePeselsInDb;
import pl.damiankaplon.devcompany.service.exception.NoClientsFound;
import pl.damiankaplon.devcompany.service.exception.NotSpecifiedAllArguments;
import pl.damiankaplon.devcompany.service.exception.PeselAlreadyInDb;
import pl.damiankaplon.devcompany.service.exception.NotSpecifiedReqArgs;

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
        Session session = openSession();
        Query q = session.createQuery("from Client where pesel='"+client.getPesel()+"'");
        List<Client> clients = q.getResultList();
        Optional<Client> optionalClient = clients.stream().findAny();
        if(optionalClient.isPresent()) throw new PeselAlreadyInDb(session);
        session.beginTransaction();
        session.save(client);
        session.getTransaction().commit();
        session.close();
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

    public void updateClient(Client client) throws ManySamePeselsInDb, NotSpecifiedReqArgs {
        if (client.getPesel().isBlank() || client.getPesel().isBlank()) throw new NotSpecifiedReqArgs();

        Session fetchSession = openSession();
        Query q = fetchSession.createQuery("from Client where pesel='"+client.getPesel()+"'");
        List<Client> clients = q.getResultList();
        fetchSession.close();

        if(clients.size() > 1) throw new ManySamePeselsInDb();

        Session updateSession = openSession();
        updateSession.beginTransaction();
        updateSession.update(new Client(clients.get(0).getId(), client.getName(), client.getSurname(), client.getPesel()));
        updateSession.getTransaction().commit();
        updateSession.close();
    }

    private Session openSession(){
        return this.sessionFactory.openSession();
    }

}
