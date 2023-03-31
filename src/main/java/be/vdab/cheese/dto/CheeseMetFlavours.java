package be.vdab.cheese.dto;

import be.vdab.cheese.domain.Flavour;

import java.util.Set;
import java.util.stream.Stream;

public record CheeseMetFlavours(String name, Stream<String> flavours) {
      public CheeseMetFlavours(String cheeseName,Set<Flavour> flavours){
        this(cheeseName,flavours.stream().map(flavour -> flavour.getName()));
    }
}
