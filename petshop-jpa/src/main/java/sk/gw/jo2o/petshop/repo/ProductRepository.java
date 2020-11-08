package sk.gw.jo2o.petshop.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import sk.gw.jo2o.petshop.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByNameStartsWithAndPriceBetween(String nameStart, int priceFrom, int priceTo, Pageable pageable);

}
