package com.mentorama.jpaapi.repository;

import com.mentorama.jpaapi.entity.ProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository <ProductEntity, Long> {
    List<ProductEntity> findByDescriptionContaining(String description);

    //  @Query("SELECT * FROM products p WHERE p.value > ?1", nativeQuery = true)
    @Query("SELECT p FROM ProductEntity p WHERE p.value > ?1")
    List<ProductEntity> findProductByValue(Double value);
}
