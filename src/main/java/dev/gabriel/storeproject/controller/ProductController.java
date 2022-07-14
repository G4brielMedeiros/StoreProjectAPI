package dev.gabriel.storeproject.controller;

import dev.gabriel.storeproject.controller.util.URL;
import dev.gabriel.storeproject.domain.Product;
import dev.gabriel.storeproject.dto.ProductDTO;
import dev.gabriel.storeproject.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
@RequiredArgsConstructor
public class ProductController {

    final ProductService service;

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Integer id) {

        Product product = service.findById(id);
        return ResponseEntity.ok().body(product);
    }

    @GetMapping()
    public ResponseEntity<Page<ProductDTO>> findPage(
            @RequestParam(defaultValue = "0") String name,
            @RequestParam(defaultValue = "0") String categories,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "24") Integer size,
            @RequestParam(defaultValue = "name") String orderBy,
            @RequestParam(defaultValue = "ASC") String direction
    ) {

        List<Integer> ids = URL.decodeIntegerList(categories);
        String decodedName = URL.decodeParam(name);

        Page<ProductDTO> dtoPage = service.search(decodedName, ids, page, size, orderBy, direction);
        return ResponseEntity.ok(dtoPage);
    }
}
