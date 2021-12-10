package pl.damiankaplon.devcompany.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.damiankaplon.devcompany.model.Building;
import pl.damiankaplon.devcompany.model.Client;
import pl.damiankaplon.devcompany.model.Flat;
import pl.damiankaplon.devcompany.model.Sale;
import pl.damiankaplon.devcompany.service.exception.SaleAlreadyExists;

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

    public void save(Sale sale) throws SaleAlreadyExists {
        Sale saleTest = getSaleByIdentity(sale.getIdentity());
        if (saleTest != null) throw new SaleAlreadyExists();


    }

    private void prepareCriteria() {
        this.session = this.sessionFactory.openSession();
        this.cb = this.session.getCriteriaBuilder();
        this.cq = this.cb.createQuery(Sale.class);
        this.root = this.cq.from(Sale.class);
    }
}
