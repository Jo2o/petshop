package sk.gw.jo2o.petshop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import sk.gw.jo2o.petshop.model.Product;

@Service
public class ProductService {

    public List<Product> getProducts() {
        return new ArrayList<>();
    }

}
