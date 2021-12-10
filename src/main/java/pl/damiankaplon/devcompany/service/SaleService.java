package pl.damiankaplon.devcompany.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.damiankaplon.devcompany.model.Building;
import pl.damiankaplon.devcompany.model.Client;
import pl.damiankaplon.devcompany.model.Flat;
import pl.damiankaplon.devcompany.model.Sale;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SaleService {

    private final SessionFactory sessionFactory;

    public SaleService(SessionFactory sessionFactory) throws NoResultException {
        this.sessionFactory = sessionFactory;
    }
    public Sale getSaleByIdentity(String saleNr) {
        Session session = this.sessionFactory.openSession();
        Query q = session.createQuery("from Sale where identity='"+saleNr+"'");
        Sale sale = (Sale) q.getSingleResult();
        session.close();
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
}
