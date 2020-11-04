package com.unlam.library.interfaces;

import java.util.List;
import java.util.Optional;

public interface Storable<T> {

    T upsert(T object);
    Boolean delete(T object);
    Boolean deleteBy(Long id);
    List<T> findAll();
    Optional<T> findById(Long id);
}
