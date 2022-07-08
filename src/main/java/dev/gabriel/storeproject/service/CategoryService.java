package dev.gabriel.storeproject.service;

import dev.gabriel.storeproject.domain.Category;
import dev.gabriel.storeproject.repository.CategoryRepository;
import dev.gabriel.storeproject.service.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService implements EntityService<Category>{

    final CategoryRepository repository;

    public Category findById(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("" + Category.class.getSimpleName() + " not found. Id: " + id));
    }


}
