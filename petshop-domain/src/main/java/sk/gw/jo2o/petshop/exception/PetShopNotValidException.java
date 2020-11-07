package sk.gw.jo2o.petshop.exception;

public class PetShopNotValidException extends RuntimeException {

    public PetShopNotValidException(String message) {
        super(message);
    }

    public PetShopNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

}
