package pl.damiankaplon.devcompany.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.damiankaplon.devcompany.model.Building;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class BuildingService {

    private final SessionFactory sessionFactory;
    private Session session;
    private CriteriaBuilder cb;
    private CriteriaQuery<Building> cq;
    private Root<Building> root;

    public BuildingService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Building getBuilding(Building building) {
        this.prepareCriteria();
        Predicate predicatePostal = cb.equal(root.get("postal"), building.getPostal());
        Predicate predicateAddress = cb.equal(root.get("address"), building.getAddress());
        this.cq.where(predicatePostal, predicateAddress);
        Building resultBuilding = this.session.createQuery(this.cq).getSingleResult();
        this.session.close();
        return resultBuilding;
    }

    private void prepareCriteria() {
        this.session = this.sessionFactory.openSession();
        this.cb = this.session.getCriteriaBuilder();
        this.cq = this.cb.createQuery(Building.class);
        this.root = this.cq.from(Building.class);
    }
}
