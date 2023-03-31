package be.vdab.cheese.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class FlavourHeeftDezeCheeseAlException extends RuntimeException {
    public FlavourHeeftDezeCheeseAlException() {
        super("Flavour heeft deze cheese al.");
    }
}
