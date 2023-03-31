package be.vdab.cheese.domain;

import be.vdab.cheese.exceptions.CheeseHeeftDezeAnimalAlException;
import be.vdab.cheese.exceptions.CheeseHeeftDezeWebsiteAlException;
import be.vdab.cheese.exceptions.FlavourHeeftDezeCheeseAlException;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "cheese")
public class Cheese {
    @Id
    private long id;
    private String name;
    private boolean vegetarian;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "countryId")
    private Country country;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "colourId")
    private Colour colour;

    @ManyToMany(mappedBy = "cheeseSet", fetch = FetchType.LAZY)
    @OrderBy("name")
    private Set<Flavour> flavours;

    @ManyToMany(mappedBy = "cheeseSet", fetch = FetchType.LAZY)
    @OrderBy("name")
    private Set<Animal> animals;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "webpages",
            joinColumns = @JoinColumn(name = "cheeseId"))
    private Set<Website> websites;

    private int likes;
    private int dislikes;
    @Version
    private int version;

    public Cheese(long id, String name, boolean vegetarian, Country country, Colour colour, int likes, int dislikes) {
        this.id = id;
        this.name = name;
        this.vegetarian = vegetarian;
        this.country = country;
        this.colour = colour;
        this.likes = likes;
        this.dislikes = dislikes;
        flavours = new LinkedHashSet<>();
        animals = new LinkedHashSet<>();
        websites = new LinkedHashSet<>();
    }

    protected Cheese() {
    }

    public void addFlavour(Flavour flavour) {
        if (!flavours.add(flavour)) {
            throw new FlavourHeeftDezeCheeseAlException();
        }
    }

    public void addAnimal(Animal animal) {
        if (!animals.add(animal)) {
            throw new CheeseHeeftDezeAnimalAlException();
        }
    }

    public void addWebsite(Website website) {
        if (!websites.add(website)) {
            throw new CheeseHeeftDezeWebsiteAlException();
        }
    }

    public void likeCheese() {
        likes = likes + 1;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public Country getCountry() {
        return country;
    }

    public Colour getColour() {
        return colour;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public Set<Flavour> getFlavours() {
        return Collections.unmodifiableSet(flavours);
    }

    public Set<Animal> getAnimals() {
        return Collections.unmodifiableSet(animals);
    }

    public Set<Website> getWebsites() {
        return Collections.unmodifiableSet(websites);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cheese cheese)) return false;
        return name.equals(cheese.name) && country.equals(cheese.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country);
    }
}

