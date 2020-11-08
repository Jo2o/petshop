package sk.gw.jo2o.petshop.exception;

public class PetShopAuthException extends RuntimeException {

    public PetShopAuthException(String message) {
        super(message);
    }

    public PetShopAuthException(String message, Throwable cause) {
        super(message, cause);
    }

}
