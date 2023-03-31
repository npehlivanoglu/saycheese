package be.vdab.cheese.dto;


import be.vdab.cheese.domain.Cheese;

public record CheeseBeknopt(String name, String countryName, String colourName) {
    public CheeseBeknopt(Cheese cheese) {
        this(cheese.getName(), cheese.getCountry().getName(), cheese.getColour().getName());
    }
}
