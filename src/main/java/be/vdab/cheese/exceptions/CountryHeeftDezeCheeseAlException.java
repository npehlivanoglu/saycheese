package be.vdab.cheese.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CountryHeeftDezeCheeseAlException extends RuntimeException {
    public CountryHeeftDezeCheeseAlException() {
        super("Country heeft deze cheese al.");
    }
}
