package sk.gw.jo2o.petshop.rest.common;

import org.springframework.stereotype.Service;

import sk.gw.jo2o.petshop.rest.products.ProductRequest;

@Service
public class ValidateService {

    public boolean isValid(ProductRequest productRequest) {
        return false;
    }
}
