package sk.gw.jo2o.petshop.rest.common;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PriceMapper {

    private static final int DECIMAL_PLACES = 2;

    private final PriceValidator priceValidator;

    public String validateAndMapToString(int price) {
        priceValidator.validate(price);
        return BigDecimal.valueOf(price).movePointLeft(DECIMAL_PLACES).toString();
    }

    public Integer validateAndMapToInt(String price) {
        if (price == null) {
            return null;
        }
        priceValidator.validate(price);
        return new BigDecimal(price).stripTrailingZeros().movePointRight(DECIMAL_PLACES).intValue();
    }

}
