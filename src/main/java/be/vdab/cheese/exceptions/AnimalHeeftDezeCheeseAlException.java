package be.vdab.cheese.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AnimalHeeftDezeCheeseAlException extends RuntimeException {
    public AnimalHeeftDezeCheeseAlException() {
        super("Animal heeft deze cheese al.");
    }
}
