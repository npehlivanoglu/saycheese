package be.vdab.cheese.dto;

import be.vdab.cheese.domain.Cheese;

public record CheeseMetNameLikesDislikes(String name, int likes, int dislikes) {
    public CheeseMetNameLikesDislikes(Cheese cheese) {
        this(cheese.getName(), cheese.getLikes(), cheese.getDislikes());
    }
}
