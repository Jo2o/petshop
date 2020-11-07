package sk.gw.jo2o.petshop.exception;

public class PetShopNotFoundException extends RuntimeException {

    public PetShopNotFoundException(String message) {
        super(message);
    }

    public PetShopNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
