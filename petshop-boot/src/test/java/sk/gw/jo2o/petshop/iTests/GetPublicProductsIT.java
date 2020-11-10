package sk.gw.jo2o.petshop.iTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;
import sk.gw.jo2o.petshop.entity.Product;
import sk.gw.jo2o.petshop.repo.ProductRepository;
import sk.gw.jo2o.petshop.rest.products.ProductShortResponse;

@SpringBootTest
@AutoConfigureMockMvc
public class GetPublicProductsIT {

    private static final long ID_1 = 1;
    private static final long ID_2 = 2;
    private static final String NAME_1 = "name-1";
    private static final String NAME_2 = "name-2";
    private static final String CATEGORIES_1 = "category-1.1,category-1.2";
    private static final String CATEGORIES_2 = "category-2.1,category-2.2";

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @SneakyThrows
    void shouldGetPublicProducts() {
        // given
        given(productRepository.findByNameStartsWithAndPriceBetween("Toy", 1000, 10005, PageRequest.of(2, 2)))
                .willReturn(createProductPage());

        // when
        ResultActions resultActions = mockMvc.perform(
                get("/v1/products/public-products")
                        .param("priceFrom", "10")
                        .param("priceTo", "100.05")
                        .param("nameStartsWith", "Toy")
                        .param("pageIndex", "2")
                        .param("pageSize", "2")
                        .header("Content-Type", "application/json"))
                .andExpect(status().isOk());

        // then
        then(productRepository).should()
                .findByNameStartsWithAndPriceBetween(eq("Toy"), eq(1000), eq(10005), eq(PageRequest.of(2, 2)));

        List<ProductShortResponse> productShortResponseList = parseJsonResponse(resultActions);

        assertThat(productShortResponseList).hasSize(2)
                .containsExactly(
                        ProductShortResponse.builder()
                                .id(ID_1)
                                .name(NAME_1)
                                .price("11.11")
                                .categories(CATEGORIES_1)
                                .build(),
                        ProductShortResponse.builder()
                                .id(ID_2)
                                .name(NAME_2)
                                .price("22.22")
                                .categories(CATEGORIES_2)
                                .build());
    }

    private Page<Product> createProductPage() {
        return new PageImpl<>(List.of(
                Product.builder()
                        .id(ID_1)
                        .name(NAME_1)
                        .price(1111)
                        .description("description-1")
                        .categories(CATEGORIES_1)
                        .imageUrls("imageUrls-1")
                        .build(),
                Product.builder()
                        .id(ID_2)
                        .name(NAME_2)
                        .price(2222)
                        .description("description-2")
                        .categories(CATEGORIES_2)
                        .imageUrls("imageUrls-2")
                        .build()));
    }

    @SneakyThrows
    private List<ProductShortResponse> parseJsonResponse(ResultActions resultActions) {
        return objectMapper.readValue(
                objectMapper.readTree(resultActions.andReturn().getResponse().getContentAsString()).findValue("content").toString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, ProductShortResponse.class));
    }

}
