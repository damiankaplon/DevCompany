package pl.damiankaplon.devcompany.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Builder
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Building building = (Building) o;
        return id != 0L && Objects.equals(this.id, building.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
