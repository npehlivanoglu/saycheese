package be.vdab.cheese.services;

import be.vdab.cheese.domain.Cheese;
import be.vdab.cheese.exceptions.CheeseNietGevondenException;
import be.vdab.cheese.repositories.CheeseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CheeseService {
    private final CheeseRepository cheeseRepository;

    public CheeseService(CheeseRepository cheeseRepository) {
        this.cheeseRepository = cheeseRepository;
    }

    public List<Cheese> findByNameContains(String name) {
        return cheeseRepository.findByNameContainsOrderByName(name);
    }

    public Optional<Cheese> findById(long id) {
        return cheeseRepository.findById(id);
    }

    ;

    @Transactional
    public int likeCheese(long id) {
        if (cheeseRepository.likeCheese(id) == 0) {
            throw new CheeseNietGevondenException();
        } else {
            return 1;
        }

    }


}
