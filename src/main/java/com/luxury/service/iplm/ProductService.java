package com.luxury.service.iplm;

import com.luxury.model.Category;
import com.luxury.model.Product;
import com.luxury.repository.ProductRepository;
import com.luxury.service.itf.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findOneById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);

    }

    @Override
    public List<Product> findByName(Pageable pageable, String name,List<Category> category) {
        return productRepository.findAllByNameContainsIgnoreCaseAndCategoryIn(pageable,name,category);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> findByCategoryIn(Pageable pageable, List<Category> category) {
        return productRepository.findByCategoryIn(pageable,category);
    }

    @Override
    public Page<Product> findByNameOnly(Pageable pageable, String name) {
        return productRepository.findAllByNameContaining(pageable,name);
    }
}
