package be.vdab.cheese.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "colours")
public class Colour {
    @Id
    private long id;
    private String name;

    public Colour(long id, String name) {
        this.name = name;
    }
    protected Colour(){}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Colour colour)) return false;
        return name.equals(colour.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
