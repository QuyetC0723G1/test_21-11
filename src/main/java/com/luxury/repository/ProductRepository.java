package com.luxury.repository;

import com.luxury.model.Category;
import com.luxury.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByNameContainsIgnoreCaseAndCategoryIn(Pageable pageable,String name,List<Category> category);
    Page<Product> findByCategoryIn(Pageable pageable, List<Category> category);
    Page<Product> findAllByNameContaining(Pageable pageable,String name);
}
