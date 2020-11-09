package sk.gw.jo2o.petshop.shopping.services;

import static java.util.Objects.requireNonNullElse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sk.gw.jo2o.petshop.entity.Product;
import sk.gw.jo2o.petshop.exception.PetShopNotFoundException;
import sk.gw.jo2o.petshop.repo.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product find(long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new PetShopNotFoundException("Cannot find product with ID: " + id));
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public Page<Product> getPagedProducts(String nameStartsWith, Integer priceFrom, Integer priceTo, PageRequest pageRequest) {
        return productRepository.findByNameStartsWithAndPriceBetween(
                processNameStartsWith(nameStartsWith),
                processPriceFrom(priceFrom),
                processPriceTo(priceTo),
                pageRequest);
    }

    public Page<Product> getPagedProducts(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest);
    }

    private String processNameStartsWith(String nameStartsWith) {
        return requireNonNullElse(nameStartsWith, "");
    }

    private int processPriceFrom(Integer priceFrom) {
        return requireNonNullElse(priceFrom, 0);
    }

    private int processPriceTo(Integer priceTo) {
        return requireNonNullElse(priceTo, Integer.MAX_VALUE);
    }



}
