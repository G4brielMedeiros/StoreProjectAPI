package dev.gabriel.storeproject.controller;

import dev.gabriel.storeproject.domain.Purchase;
import dev.gabriel.storeproject.service.entity.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/purchases")
@RequiredArgsConstructor
public class PurchaseController {

    final PurchaseService service;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody Purchase obj) {
        Purchase purchase = service.add(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(purchase.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Purchase> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<Purchase>> findPage(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "24") Integer size,
            @RequestParam(defaultValue = "instant") String orderBy,
            @RequestParam(defaultValue = "DESC") String direction
    ) {
        Page<Purchase> purchasePage = service.findPage(page, size, orderBy, StringUtils.capitalize(direction));
        return ResponseEntity.ok(purchasePage);
    }
}
