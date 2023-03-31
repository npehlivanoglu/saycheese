package be.vdab.cheese.repositories;

import be.vdab.cheese.domain.Cheese;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CheeseRepository extends JpaRepository<Cheese, Long> {
    @EntityGraph(attributePaths = {"country", "colour"})
    List<Cheese> findByNameContainsOrderByName(String name);

    @EntityGraph(attributePaths = {"animals", "flavours"})
    Optional<Cheese> findById(long id);

}
