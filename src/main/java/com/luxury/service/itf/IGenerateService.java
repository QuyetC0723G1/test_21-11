package com.luxury.service.itf;

import java.util.Optional;

public interface IGenerateService<T>{
    Iterable<T> findAll();
    Optional<T> findOneById(Long id);
    void save(T t);
    void delete(Long id);

}
