package be.vdab.cheese.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CheeseHeeftDezeFlavourAlException extends RuntimeException {
    public CheeseHeeftDezeFlavourAlException() {
        super("Cheese heeft deze flavour al.");
    }
}
