package pl.damiankaplon.devcompany.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flats")
public class Flat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flat_id", nullable = false) private long id;

    @ManyToOne
    @JoinColumn(name = "building_id", referencedColumnName = "building_id")
    private Building building;

    @ManyToOne
    @JoinColumn(name = "sale_id", referencedColumnName = "sale_id")
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    private Client client;

    @Column(name = "flat_number") private int flatNumber;
}
