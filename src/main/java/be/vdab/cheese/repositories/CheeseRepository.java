package be.vdab.cheese.repositories;

import be.vdab.cheese.domain.Cheese;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.*;

import java.util.List;
import java.util.Optional;

public interface CheeseRepository extends JpaRepository<Cheese, Long> {
    @EntityGraph(attributePaths = {"country", "colour"})
    List<Cheese> findByNameContainsOrderByName(String name);

    @EntityGraph(attributePaths = {"animals", "flavours", "websites"})
    Optional<Cheese> findById(long id);

    @Query("select c from Cheese c where c.id=:id")
    @EntityGraph(attributePaths = {"animals", "flavours", "websites"})
    @Lock(LockModeType.PESSIMISTIC_READ)
    Optional<Cheese> findAndLock(long id);

    @Modifying
    @Query("update Cheese c set c.likes = c.likes+1 where c.id = :id")
    int likeCheese(long id);
}
