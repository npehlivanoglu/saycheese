package be.vdab.cheese.dto;

import be.vdab.cheese.domain.Country;

import java.util.List;
import java.util.stream.Stream;

public record CountryMetCheeses(String countryName, Stream<CheeseMetNameLikesDislikes> cheeses) {
    public CountryMetCheeses(Country country) {
        this(country.getName(), country.getCheeses().stream().map(CheeseMetNameLikesDislikes::new));

    }
}
