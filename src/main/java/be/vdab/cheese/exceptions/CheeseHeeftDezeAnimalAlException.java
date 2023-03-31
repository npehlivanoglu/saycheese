package be.vdab.cheese.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CheeseHeeftDezeAnimalAlException extends RuntimeException {
    public CheeseHeeftDezeAnimalAlException() {
        super("Cheese heeft deze animal al");
    }
}
