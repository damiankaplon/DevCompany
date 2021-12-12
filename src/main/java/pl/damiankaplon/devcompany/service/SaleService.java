package pl.damiankaplon.devcompany.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.damiankaplon.devcompany.model.Building;
import pl.damiankaplon.devcompany.model.Client;
import pl.damiankaplon.devcompany.model.Flat;
import pl.damiankaplon.devcompany.model.Sale;
import pl.damiankaplon.devcompany.service.exception.NoClientsFound;
import pl.damiankaplon.devcompany.service.exception.NoSuchBuilding;
import pl.damiankaplon.devcompany.service.exception.NoSuchFlat;
import pl.damiankaplon.devcompany.service.exception.SaleAlreadyExists;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;


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

    public void save(Sale sale) throws SaleAlreadyExists, NoSuchBuilding, NoSuchFlat, NoClientsFound {
        try {
            getSaleByIdentity(sale.getIdentity());
            throw new SaleAlreadyExists();
        } catch (NoResultException ignored) {}

        Building properBuilding = getProperBuildingForSale(sale);
        sale.getFlat().get(0).setBuilding(properBuilding);
        sale.setBuilding(properBuilding);

        Flat properFlat = getProperFlatForSale(sale);
        sale.setFlat(List.of(properFlat));

        Client properClient = getProperClientForSale(sale);
        sale.setClient(properClient);

        sale.setBuilding(properBuilding);
        sale.setFlat(List.of(properFlat));

        saveTransaction(sale);

        Sale thisSaleFromDb = getSaleByIdentity(sale.getIdentity());
        properFlat.setSale(thisSaleFromDb);
        saveTransaction(properFlat);
    }

    private Building getProperBuildingForSale(Sale sale) throws NoSuchBuilding {
        BuildingService buildingService = new BuildingService(this.sessionFactory);
        try {
            return buildingService.getBuilding(sale.getBuilding());
        }
        catch (NoResultException e) {
            e.printStackTrace();
            NoSuchBuilding ex = new NoSuchBuilding();
            ex.initCause(e);
            throw ex;
        }
    }

    private Flat getProperFlatForSale(Sale sale) throws NoSuchFlat {
        FlatService flatService = new FlatService(this.sessionFactory);
        try {
            return flatService.getFlat(sale.getFlat().get(0));
        }
        catch (NoResultException e) {
            e.printStackTrace();
            NoSuchFlat ex = new NoSuchFlat();
            ex.initCause(e);
            throw ex;
        }
    }

    private Client getProperClientForSale(Sale sale) throws NoClientsFound {
        ClientService clientService = new ClientService(this.sessionFactory);
        try {
            return clientService.getClientByAllArgs(sale.getClient()).get(0);
        }
        catch (NoResultException e) {
            e.printStackTrace();
            NoClientsFound ex = new NoClientsFound(this.session);
            ex.initCause(e);
            throw ex;
        }
    }

    private void prepareCriteria() {
        this.session = this.sessionFactory.openSession();
        this.cb = this.session.getCriteriaBuilder();
        this.cq = this.cb.createQuery(Sale.class);
        this.root = this.cq.from(Sale.class);
    }

    private void saveTransaction(Object ob) {
        prepareCriteria();
        this.session.beginTransaction();
        this.session.save(ob);
        this.session.getTransaction().commit();
        this.session.close();
    }
}
