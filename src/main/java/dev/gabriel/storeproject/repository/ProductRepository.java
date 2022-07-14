package dev.gabriel.storeproject.repository;

import dev.gabriel.storeproject.domain.Category;
import dev.gabriel.storeproject.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Transactional(readOnly = true)
    Page<Product> findDistinctByNameContainingIgnoreCaseAndCategoriesIn(String name, List<Category> categories, Pageable pageRequest);

}