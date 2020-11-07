package sk.gw.jo2o.petshop.rest.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import sk.gw.jo2o.petshop.exception.PetShopNotValidException;

@ExtendWith(MockitoExtension.class)
class PriceMapperTest {

    @InjectMocks
    private PriceMapper priceMapper;

    @Test
    void shouldConvertAndMapToStr() {
        // when
        String result1 = priceMapper.validateAndMapToString(0);
        String result2 = priceMapper.validateAndMapToString(1);
        String result3 = priceMapper.validateAndMapToString(123);
        // then
        assertThat(result1).isEqualTo("0.00");
        assertThat(result2).isEqualTo("0.01");
        assertThat(result3).isEqualTo("1.23");
    }

    @Test
    void shouldThrowInvalidExceptionOnNegativeInt() {
        // when
        Throwable throwable = catchThrowable(() -> priceMapper.validateAndMapToString(-1));
        // then
        assertThat(throwable).isInstanceOf(PetShopNotValidException.class);
    }

    @Test
    void shouldMapToInt() {
        // when
        int result1 = priceMapper.validateAndMapToInt("0.05000");
        int result2 = priceMapper.validateAndMapToInt("123");
        // then
        assertThat(result1).isEqualTo(5);
        assertThat(result2).isEqualTo(12300);
    }

    @Test
    void shouldThrowInvalidExceptionOnInvalidPriceString() {
        // when
        Throwable throwable1 = catchThrowable(() -> priceMapper.validateAndMapToInt("0.005"));
        Throwable throwable2 = catchThrowable(() -> priceMapper.validateAndMapToInt("-0.05"));
        // then
        assertThat(throwable1).isInstanceOf(PetShopNotValidException.class);
        assertThat(throwable2).isInstanceOf(PetShopNotValidException.class);
    }

}
