package pl.damiankaplon.devcompany.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.damiankaplon.devcompany.model.Flat;
import pl.damiankaplon.devcompany.model.Sale;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class FlatService {

    private final SessionFactory sessionFactory;
    private Session session;
    private CriteriaBuilder cb;
    private CriteriaQuery<Flat> cq;
    private Root<Flat> root;

    public FlatService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Flat getFlat(Flat flat) {
        this.prepareCriteria();
        Predicate predicateFlatNumber = cb.equal(root.get("flatNumber"), flat.getFlatNumber());
        Predicate predicateBuilding = cb.equal(root.get("building"), flat.getBuilding());
        this.cq.where(predicateBuilding, predicateFlatNumber);
        Flat resultFlat = this.session.createQuery(this.cq).getSingleResult();
        this.session.close();
        return resultFlat;
    }

    public void update(Flat flat) {
        this.session = this.sessionFactory.openSession();
        this.session.beginTransaction();
        this.session.update(flat);
        this.session.getTransaction().commit();
        this.session.close();
    }

    private void prepareCriteria() {
        this.session = this.sessionFactory.openSession();
        this.cb = this.session.getCriteriaBuilder();
        this.cq = this.cb.createQuery(Flat.class);
        this.root = this.cq.from(Flat.class);
    }
}
