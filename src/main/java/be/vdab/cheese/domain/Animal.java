package be.vdab.cheese.domain;

import be.vdab.cheese.exceptions.AnimalHeeftDezeCheeseAlException;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "animals")
public class Animal {
    @Id
    private long id;
    private String name;
    @ManyToMany
    @JoinTable(name = "cheeseanimals",
            joinColumns = @JoinColumn(name = "animalId"),
            inverseJoinColumns = @JoinColumn(name = "cheeseId"))
    private Set<Cheese> cheeseSet;

    public Animal(long id, String name) {
        this.id = id;
        this.name = name;
        this.cheeseSet = new LinkedHashSet<>();
    }
    protected Animal(){}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Cheese> getCheeseSet() {
        return Collections.unmodifiableSet(cheeseSet);
    }

    public void addCheese(Cheese cheese) {
        if (!cheeseSet.add(cheese)) {
            throw new AnimalHeeftDezeCheeseAlException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal animal)) return false;
        return name.equals(animal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
