package be.vdab.cheese.controllers;

import be.vdab.cheese.domain.Animal;
import be.vdab.cheese.domain.Website;
import be.vdab.cheese.dto.CheeseBeknopt;
import be.vdab.cheese.dto.CheeseMetFlavours;
import be.vdab.cheese.exceptions.CheeseNietGevondenException;
import be.vdab.cheese.services.CheeseService;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping("cheese")
class CheeseController {
    private final CheeseService cheeseService;

    CheeseController(CheeseService cheeseService) {
        this.cheeseService = cheeseService;
    }

    @GetMapping(params = "nameContains")
    Stream<CheeseBeknopt> findByNameContains(String nameContains) {
        return cheeseService.findByNameContains(nameContains)
                .stream()
                .map(CheeseBeknopt::new);
    }

    @GetMapping("{id}/animals")
    Stream<String> findByIdAnimals(@PathVariable int id) {
        return cheeseService.findById(id)
                .orElseThrow(CheeseNietGevondenException::new)
                .getAnimals().stream().map(Animal::getName);
    }

    @GetMapping("{id}/withFlavours")
    CheeseMetFlavours findByIdMetFlavours(@PathVariable long id) {
        var cheese = cheeseService.findById(id)
                .orElseThrow(CheeseNietGevondenException::new);
        return new CheeseMetFlavours(cheese.getName(), cheese.getFlavours());
    }

    @GetMapping("{id}/webpages")
    Stream<String> findWebpagesById(@PathVariable long id) {
        return cheeseService.findById(id).orElseThrow(CheeseNietGevondenException::new)
                .getWebsites()
                .stream()
                .map(Website::getUrl);
    }

    @PostMapping("{id}/likes")
    int likeCheese(@PathVariable long id) {
        return cheeseService.likeCheese(id);
    }
}
