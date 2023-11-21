package com.luxury.service.itf;

import com.luxury.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService extends IGenerateService<Product> {
    Page<Product> findByName(Pageable pageable, String name);
    Page<Product> findAll(Pageable pageable);
}
