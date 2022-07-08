package dev.gabriel.storeproject.resource;

import dev.gabriel.storeproject.domain.Category;
import dev.gabriel.storeproject.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categories")
@RequiredArgsConstructor
public class CategoryResource {

    final CategoryService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {

        Category category = service.findById(id);
        return ResponseEntity.ok().body(category);
    }
}
