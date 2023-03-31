package be.vdab.cheese.services;

import be.vdab.cheese.domain.Country;
import be.vdab.cheese.repositories.CountryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Optional<Country> findById(long id) {
        return countryRepository.findById(id);
    }
}
