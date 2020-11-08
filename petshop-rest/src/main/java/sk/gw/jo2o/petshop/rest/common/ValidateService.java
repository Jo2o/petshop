package sk.gw.jo2o.petshop.rest.common;

import org.springframework.stereotype.Service;

import sk.gw.jo2o.petshop.rest.product.ProductRequest;

@Service
public class ValidateService {

    public boolean isValid(ProductRequest productRequest) {
        return false;
    }
}
