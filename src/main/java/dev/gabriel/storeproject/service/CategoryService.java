package dev.gabriel.storeproject.service;

import dev.gabriel.storeproject.domain.Category;
import dev.gabriel.storeproject.repository.CategoryRepository;
import dev.gabriel.storeproject.service.exception.DataIntegrityException;
import dev.gabriel.storeproject.service.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService implements EntityService<Category>{

    final CategoryRepository repository;

    public Category findById(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("" + Category.class.getSimpleName() + " not found. Id: " + id));
    }

    public Category add(Category obj) {
        obj.setId(null);
        return repository.save(obj);
    }

    public Category update(Category obj) {
        findById(obj.getId());
        return repository.save(obj);
    }

    public void deleteById(Integer id) {
        findById(id);

        try { repository.deleteById(id); }
        catch (DataIntegrityViolationException exception) {
            throw new DataIntegrityException("Cannot delete a category that has products.");
        }
    }

}
