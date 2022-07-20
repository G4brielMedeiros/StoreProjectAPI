package dev.gabriel.storeproject.controller;

import dev.gabriel.storeproject.domain.Purchase;
import dev.gabriel.storeproject.service.entity.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Void> create( @Valid @RequestBody Purchase obj) {
        Purchase purchase = service.add(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(purchase.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Purchase> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }
}
