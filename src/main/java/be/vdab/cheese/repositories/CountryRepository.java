package be.vdab.cheese.repositories;

import be.vdab.cheese.domain.Country;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {

    @EntityGraph(attributePaths = "cheeses")
    Optional<Country> findById(long id);
}
