package sk.gw.jo2o.petshop.shopping.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sk.gw.jo2o.petshop.entity.Product;
import sk.gw.jo2o.petshop.exception.PetShopNotFoundException;
import sk.gw.jo2o.petshop.repo.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product getProduct(long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new PetShopNotFoundException("Cannot find product with ID: " + id));
    }

    public void save(Product product) {
        productRepository.save(product);
    }

}
