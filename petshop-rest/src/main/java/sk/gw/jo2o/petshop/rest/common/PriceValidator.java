package sk.gw.jo2o.petshop.rest.common;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sk.gw.jo2o.petshop.exception.PetShopNotValidException;

@Service
@RequiredArgsConstructor
public class PriceValidator {

    private static final String INVALID_PRICE_MESSAGE = "Invalid price string: ";

    public void validate(int price) {
        if (price < 0) {
            throw new PetShopNotValidException("Cannot convert negative price: " + price);
        }
    }

    public void validate(String price) {
        BigDecimal bigDecimalPrice;
        try {
            bigDecimalPrice = new BigDecimal(price).stripTrailingZeros();
        } catch (RuntimeException e) {
            throw new PetShopNotValidException(INVALID_PRICE_MESSAGE + price);
        }
        if (bigDecimalPrice.scale() > 2) {
            throw new PetShopNotValidException(INVALID_PRICE_MESSAGE + price);
        }
        if (bigDecimalPrice.compareTo(ZERO) < 0) {
            throw new PetShopNotValidException(INVALID_PRICE_MESSAGE + price);
        }
    }

}
