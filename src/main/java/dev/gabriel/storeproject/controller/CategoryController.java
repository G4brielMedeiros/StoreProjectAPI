package dev.gabriel.storeproject.controller;

import dev.gabriel.storeproject.domain.Category;
import dev.gabriel.storeproject.dto.CategoryDTO;
import dev.gabriel.storeproject.service.entity.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
@RequiredArgsConstructor
public class CategoryController {

    final CategoryService service;

    @PostMapping
    public ResponseEntity<Void> create( @Valid @RequestBody CategoryDTO dto) {
        Category category = service.add(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(category.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Integer id) {
        Category category = service.findById(id);
        return ResponseEntity.ok(category);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<CategoryDTO> categoryList = service.findAll().stream().map(CategoryDTO::new).toList();
        return ResponseEntity.ok(categoryList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody CategoryDTO dto, @PathVariable Integer id) {
        dto.setId(id);
        service.update(dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/page")
    public ResponseEntity<Page<CategoryDTO>> findPage(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "24") Integer size,
            @RequestParam(defaultValue = "name") String orderBy,
            @RequestParam(defaultValue = "ASC") String direction
    ) {
        Page<CategoryDTO> dtoPage = service.findPage(page, size, orderBy, StringUtils.capitalize(direction));
        return ResponseEntity.ok(dtoPage);
    }
}
