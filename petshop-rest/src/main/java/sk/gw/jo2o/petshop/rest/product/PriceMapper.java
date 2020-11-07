package sk.gw.jo2o.petshop.rest.product;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import sk.gw.jo2o.petshop.exception.PetShopNotValidException;

@Service
public class PriceMapper {

    private static final int DECIMAL_PLACES = 2;
    private static final String INVALID_PRICE_MESSAGE = "Invalid price string: ";

    public String validateAndMapToString(int price) {
        if (price < 0) {
            throw new PetShopNotValidException("Cannot convert negative price: " + price);
        }
        return BigDecimal.valueOf(price).movePointLeft(DECIMAL_PLACES).toString();
    }

    public int validateAndMapToInt(String price) {
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
        return bigDecimalPrice.movePointRight(DECIMAL_PLACES).intValue();
    }

}
