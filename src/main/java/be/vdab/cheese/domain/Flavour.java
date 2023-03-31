package be.vdab.cheese.domain;

import be.vdab.cheese.exceptions.FlavourHeeftDezeCheeseAlException;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "flavours")
public class Flavour {
    @Id
    private long id;
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "cheeseflavours",
            joinColumns = @JoinColumn(name = "flavourId"),
            inverseJoinColumns = @JoinColumn(name = "cheeseId"))
    private Set<Cheese> cheeseSet;

    public Flavour(long id, String name) {
        this.id = id;
        this.name = name;
        cheeseSet = new LinkedHashSet<>();
    }

    protected Flavour() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addCheese(Cheese cheese) {
        if (!cheeseSet.add(cheese)) {
            throw new FlavourHeeftDezeCheeseAlException();
        }
    }

    public Set<Cheese> getCheeseSet() {
        return Collections.unmodifiableSet(cheeseSet);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flavour flavour)) return false;
        return Objects.equals(name, flavour.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
