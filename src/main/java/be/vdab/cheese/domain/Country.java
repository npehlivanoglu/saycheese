package be.vdab.cheese.domain;

import be.vdab.cheese.exceptions.CountryHeeftDezeCheeseAlException;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country {
    @Id
    private int id;
    private String name;

    @OneToMany(mappedBy = "country")
    @OrderBy("name")
    private Set<Cheese> cheeses;

    public Set<Cheese> getCheeses() {
        return Collections.unmodifiableSet(cheeses);
    }

    public void addCheese(Cheese cheese) {
        if (!cheeses.add(cheese)) {
            throw new CountryHeeftDezeCheeseAlException();
        }
    }

    public Country(long id, String name) {
        this.name = name;
    }

    protected Country() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country country)) return false;
        return name.equals(country.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
