package pl.damiankaplon.devcompany.model.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class Client {
    private long client_id;
    private String client_name, client_surname, client_pesel;
}
