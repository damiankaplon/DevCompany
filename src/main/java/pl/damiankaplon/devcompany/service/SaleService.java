package pl.damiankaplon.devcompany.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.damiankaplon.devcompany.model.Building;
import pl.damiankaplon.devcompany.model.Client;
import pl.damiankaplon.devcompany.model.Flat;
import pl.damiankaplon.devcompany.model.Sale;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


public class SaleService {

    private final SessionFactory sessionFactory;
    private Session session;
    private CriteriaBuilder cb;
    private CriteriaQuery<Sale> cq;
    private Root<Sale> root;

    public SaleService(SessionFactory sessionFactory) throws NoResultException {
        this.sessionFactory = sessionFactory;
    }

    public Sale getSaleByIdentity(String saleNr) {
        this.prepareCriteria();
        this.cq.select(root).where(cb.equal(root.get("identity"), saleNr));
        Sale sale = this.session.createQuery(this.cq).getSingleResult();
        this.session.close();
        return sale;
    }

    public void save(String saleNr, String saleValue, String pesel, String city, String postal, String street, String address, String aNr) {
        Session session = this.sessionFactory.openSession();
        Query q = session.createQuery("from Building where address='"+address+"' AND city='"+city+"' AND street='"+street+"'");
        Building building = (Building) q.getSingleResult();
        q = session.createQuery("from Client where pesel='"+pesel+"'");
        Client client = (Client) q.getSingleResult();
        q = session.createQuery("from Flat where building='"+building.getId()+"' and flatNumber='"+aNr+"'");
        Flat flat = (Flat) q.getResultList().get(0);
        Sale sale = Sale.builder()
                .client(client)
                .building(building)
                .flat(new ArrayList<>(List.of(flat)))
                .identity(saleNr)
                .build();
        session.beginTransaction();
        session.save(sale);
        session.getTransaction().commit();
        session.close();
    }

    private void prepareCriteria() {
        this.session = this.sessionFactory.openSession();
        this.cb = this.session.getCriteriaBuilder();
        this.cq = this.cb.createQuery(Sale.class);
        this.root = this.cq.from(Sale.class);
    }
}
