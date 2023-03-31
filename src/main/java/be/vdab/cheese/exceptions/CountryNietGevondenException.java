package be.vdab.cheese.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CountryNietGevondenException extends RuntimeException {
    public CountryNietGevondenException() {
        super("Country niet gevonden.");
    }
}
