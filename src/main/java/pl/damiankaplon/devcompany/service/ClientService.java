package pl.damiankaplon.devcompany.service;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.damiankaplon.devcompany.model.Client;
import pl.damiankaplon.devcompany.service.exception.ManySamePeselsInDb;
import pl.damiankaplon.devcompany.service.exception.NoClientsFound;
import pl.damiankaplon.devcompany.service.exception.NotSpecifiedAllArguments;
import pl.damiankaplon.devcompany.service.exception.PeselAlreadyInDb;
import pl.damiankaplon.devcompany.service.exception.NotSpecifiedReqArgs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;


public class ClientService {

    private final SessionFactory sessionFactory;
    private Session session;
    private CriteriaBuilder cb;
    private CriteriaQuery<Client> cq;
    private Root<Client> root;

    public ClientService(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public void saveClient(Client client) throws PeselAlreadyInDb, NotSpecifiedAllArguments {
        if(client.getName().equals("") || client.getSurname().equals("") || client.getPesel().equals("")){
            throw new NotSpecifiedAllArguments();
        }

        this.prepareCriteria();
        this.cq.select(root).where(cb.equal(root.get("pesel"), client.getPesel()));

        List<Client> clients = this.session.createQuery(cq).getResultList();

        Optional<Client> optionalClient = clients.stream().findAny();
        if(optionalClient.isPresent()) throw new PeselAlreadyInDb(this.session);
        this.session.beginTransaction();
        this.session.save(client);
        this.session.getTransaction().commit();
        this.session.close();
    }

    public List<Client> getClientByAllArgs(Client client) throws NoClientsFound {
        this.prepareCriteria();
        this.cq.select(root).where(cb.or((cb.equal(root.get("surname"), client.getSurname())),
                (cb.equal(root.get("pesel"), client.getPesel()))));

        final List<Client> clients = this.session.createQuery(cq).getResultList();

        if (clients.isEmpty()) throw new NoClientsFound(this.session);

        this.session.close();
        return clients;
    }

    public void updateClient(Client client) throws ManySamePeselsInDb, NotSpecifiedReqArgs {
        if (client.getPesel().isEmpty() || client.getName().isBlank() || client.getSurname().isBlank()) throw new NotSpecifiedReqArgs();

        this.prepareCriteria();

        this.cq.select(root).where(cb.or((cb.equal(root.get("surname"), client.getSurname())),
                (cb.equal(root.get("pesel"), client.getPesel()))));

        List<Client> clients = this.session.createQuery(this.cq).getResultList();
        this.session.close();

        if(clients.size() > 1) throw new ManySamePeselsInDb();
        this.update(new Client(clients.get(0).getId(), client.getName(), client.getSurname(), client.getPesel()));
    }

    public List<Client> getAllClients() {
        this.prepareCriteria();
        this.cq.select(this.root);

        return this.session.createQuery(this.cq).getResultList();
    }

    private void prepareCriteria() {
        this.session = this.sessionFactory.openSession();
        this.cb = this.session.getCriteriaBuilder();
        this.cq = this.cb.createQuery(Client.class);
        this.root = this.cq.from(Client.class);
    }

    private void update(Client client) {
        this.session = this.sessionFactory.openSession();
        this.session.beginTransaction();
        this.session.update(client);
        this.session.getTransaction().commit();
        this.session.close();
    }
}
