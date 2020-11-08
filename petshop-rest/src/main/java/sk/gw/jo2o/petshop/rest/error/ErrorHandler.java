package sk.gw.jo2o.petshop.rest.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import sk.gw.jo2o.petshop.exception.*;

@RestControllerAdvice
public class ErrorHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PetShopNotFoundException.class)
    ErrorResponse notFound(PetShopNotFoundException e) {
        return ErrorResponse.builder().message(e.getMessage()).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PetShopNotValidException.class)
    ErrorResponse notValid(PetShopNotValidException e) {
        return ErrorResponse.builder().message(e.getMessage()).build();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(PetShopAuthException.class)
    ErrorResponse notAuthorized(PetShopAuthException e) {
        return ErrorResponse.builder().message(e.getMessage()).build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    ErrorResponse defaultError(Exception e) {
        return ErrorResponse.builder().message(e.getMessage()).build();
    }

}
