package pl.damiankaplon.devcompany.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class Client {
    private long id;
    private String name, surname, pesel;

    public Client(String name, String surname, String pesel){
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
    }
}
