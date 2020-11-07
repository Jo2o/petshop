package sk.gw.jo2o.petshop.rest.product;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

@Service
public class PriceMapper {

    private static final int DECIMAL_PLACES = 2;

    public String toStr(int price) {
        return BigDecimal.valueOf(price)
                .movePointLeft(DECIMAL_PLACES)
                .toString();
    }

    public int toInt(String price) {
        return Integer.parseInt(price); // TODO: proper conversion
    }

}
