package dev.gabriel.storeproject.controller;

import dev.gabriel.storeproject.domain.Purchase;
import dev.gabriel.storeproject.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/purchases")
@RequiredArgsConstructor
public class PurchaseController {

    final PurchaseService service;

    @GetMapping("/{id}")
    public ResponseEntity<Purchase> findById(@PathVariable Integer id) {
        Purchase purchase = service.findById(id);
        return ResponseEntity.ok().body(purchase);
    }
}
