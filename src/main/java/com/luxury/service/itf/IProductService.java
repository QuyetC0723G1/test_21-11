package com.luxury.service.itf;

import com.luxury.model.Category;
import com.luxury.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService extends IGenerateService<Product> {
    List<Product> findByName(Pageable pageable, String name,List<Category> category);
    Page<Product> findAll(Pageable pageable);
    Page<Product> findByCategoryIn(Pageable pageable, List<Category> category);
    Page<Product> findByNameOnly(Pageable pageable,String name);
}
