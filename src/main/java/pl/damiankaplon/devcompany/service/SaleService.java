package pl.damiankaplon.devcompany.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.damiankaplon.devcompany.model.Building;
import pl.damiankaplon.devcompany.model.Client;
import pl.damiankaplon.devcompany.model.Flat;
import pl.damiankaplon.devcompany.model.Sale;
import pl.damiankaplon.devcompany.service.exception.*;


import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

    public void save(Sale sale) throws SaleAlreadyExists, NoSuchBuilding, NoSuchFlat, NoClientsFound, WrongSaleIdentity, FlatAlreadySoldException {
        Sale validatedSale = validateSale(sale);
        saveTransaction(sale);

        Flat SaleFlat = new FlatService(this.sessionFactory).getFlat(sale.getFlat().get(0));
        SaleFlat.setSale(validatedSale);
        new FlatService(this.sessionFactory).update(SaleFlat);
    }

    private boolean checkIfInDb(Sale sale) {
        try {
            getSaleByIdentity(sale.getIdentity());
            return true;
        } catch (NoResultException exception) {
            return false;
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

    public boolean validateRegexSaleIdentity(String saleIdentity) {
        Pattern pattern = Pattern.compile("\\d{2}[/]\\d{2}[/]\\d{4}[/]\\d{4}");
        Matcher m = pattern.matcher(saleIdentity);
        return m.matches();
    }

    private Sale validateSale(Sale sale) throws SaleAlreadyExists, WrongSaleIdentity, NoSuchBuilding, FlatAlreadySoldException, NoClientsFound {
        if (checkIfInDb(sale)) throw new SaleAlreadyExists();
        if (! validateRegexSaleIdentity(sale.getIdentity())) throw new WrongSaleIdentity();
        Building validatedBuilding = new BuildingService(this.sessionFactory).getBuilding(sale.getBuilding());
        sale.setBuilding(validatedBuilding);

        FlatService flatService = new FlatService(this.sessionFactory);
        sale.getFlat().get(0).setBuilding(validatedBuilding);
        if (flatService.checkIfSold(sale.getFlat().get(0))) throw new FlatAlreadySoldException();
        Flat validateFlat = flatService.getFlat(sale.getFlat().get(0));
        sale.setFlat(List.of(validateFlat));

        Client properClient = new ClientService(this.sessionFactory).getClientByAllArgs(sale.getClient()).get(0);
        sale.setClient(properClient);

        return sale;
    }
}
