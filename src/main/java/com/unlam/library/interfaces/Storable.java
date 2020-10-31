package com.unlam.library.interfaces;

public interface Storable<T> {

    T save(T object);
    Boolean delete(T object);
    Boolean findAll(T object);
}
