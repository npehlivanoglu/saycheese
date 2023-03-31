package be.vdab.cheese.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CheeseHeeftDezeWebsiteAlException extends RuntimeException {
    public CheeseHeeftDezeWebsiteAlException() {
        super("Cheese heeft deze website al.");
    }
}
