package sk.gw.jo2o.petshop.rest.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import sk.gw.jo2o.petshop.model.Product;

@Service
class ProductMapper {

    public List<ProductResponse> toResponsePage(List<Product> products) {
        return new ArrayList<>();
    }
}
