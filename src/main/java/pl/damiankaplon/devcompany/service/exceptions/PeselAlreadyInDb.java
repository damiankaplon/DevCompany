package pl.damiankaplon.devcompany.service.exceptions;

import org.hibernate.Session;

public class PeselAlreadyInDb extends Throwable {
    public PeselAlreadyInDb(Session session) {
        session.close();
    }
}
