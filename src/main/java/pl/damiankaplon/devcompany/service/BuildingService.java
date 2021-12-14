package pl.damiankaplon.devcompany.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.damiankaplon.devcompany.model.Building;
import pl.damiankaplon.devcompany.service.exception.NoSuchBuilding;

import javax.persistence.NoResultException;
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

    public Building getBuilding(Building building) throws NoSuchBuilding {
        this.prepareCriteria();
        Predicate predicatePostal = cb.equal(root.get("postal"), building.getPostal());
        Predicate predicateAddress = cb.equal(root.get("address"), building.getAddress());
        this.cq.where(predicatePostal, predicateAddress);
        try {
            return this.session.createQuery(this.cq).getSingleResult();
        } catch (NoResultException exception) {
            throw new NoSuchBuilding();
        } finally {
            this.session.close();
        }
    }

    private void prepareCriteria() {
        this.session = this.sessionFactory.openSession();
        this.cb = this.session.getCriteriaBuilder();
        this.cq = this.cb.createQuery(Building.class);
        this.root = this.cq.from(Building.class);
    }
}
