package dev.gabriel.storeproject.service.entity;

public interface EntityService<T> {
    T findById(Integer id);
}
