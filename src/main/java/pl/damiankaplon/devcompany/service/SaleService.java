package pl.damiankaplon.devcompany.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.damiankaplon.devcompany.model.Client;
import pl.damiankaplon.devcompany.model.Sale;

import javax.persistence.Query;
import java.util.List;

public class SaleService {

    private final SessionFactory sessionFactory;

    public SaleService(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    public Sale getSaleByIdentity(String saleNr) {
        Session session = this.sessionFactory.openSession();
        Query q = session.createQuery("from Sale where identity='"+saleNr+"'");
        Sale sale = (Sale) q.getSingleResult();
        session.close();
        return sale;
    }
}
