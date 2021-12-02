package pl.damiankaplon.devcompany.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "buildings")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "building_id", nullable = false)
    private long id;

    @Column(name = "bulding_city") private String city;

    @Column(name = "building_postal") private String postal;

    @Column(name = "building_street") private String street;

    @Column(name = "building_address") private int address;

    @OneToMany(mappedBy = "building") private List<Sale> sale;

    @OneToMany(mappedBy = "building") private List<Flat> flats;
}
