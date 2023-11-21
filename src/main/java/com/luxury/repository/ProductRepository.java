package com.luxury.repository;

import com.luxury.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findAllByNameContaining(Pageable pageable,String name);
}
