package dev.gabriel.storeproject.service;

import dev.gabriel.storeproject.domain.Category;
import dev.gabriel.storeproject.dto.CategoryDTO;
import dev.gabriel.storeproject.repository.CategoryRepository;
import dev.gabriel.storeproject.service.exception.DataIntegrityException;
import dev.gabriel.storeproject.service.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements EntityService<Category> {

    final CategoryRepository repository;

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category findById(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("" + Category.class.getSimpleName() + " not found. Id: " + id));
    }

    public Category add(CategoryDTO dto) {
        return repository.save(new Category(dto.getName()));
    }

    public void update(CategoryDTO dto) {
        Category category = findById(dto.getId());
        BeanUtils.copyProperties(dto, category);
        repository.save(category);
    }

    public void deleteById(Integer id) {
        findById(id);

        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException exception) {
            throw new DataIntegrityException("Cannot delete a category that has products.");
        }
    }

    public Page<CategoryDTO> findPage(Integer page, Integer size, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest
                .of(page, size, Sort.Direction.valueOf(StringUtils.capitalize(direction)), orderBy);

        return repository.findAll(pageRequest).map(CategoryDTO::new);
    }
}
