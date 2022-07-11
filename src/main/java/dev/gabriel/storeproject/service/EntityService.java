package dev.gabriel.storeproject.service;

public interface EntityService<T> {
    T findById(Integer id);
}
