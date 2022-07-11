package dev.gabriel.storeproject.service;

import dev.gabriel.storeproject.domain.Category;
import dev.gabriel.storeproject.repository.CategoryRepository;
import dev.gabriel.storeproject.service.exception.DataIntegrityException;
import dev.gabriel.storeproject.service.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements EntityService<Category>{

    final CategoryRepository repository;

    public List<Category> findAll() {
        return repository.findAll();
    }

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

    public Page<Category> findPage( Integer page, Integer size, String orderBy, String direction ) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);

        return repository.findAll(pageRequest);
    }

}
