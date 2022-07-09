package dev.gabriel.storeproject.controller;

import dev.gabriel.storeproject.domain.Client;
import dev.gabriel.storeproject.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clients")
@RequiredArgsConstructor
public class ClientResource {

    final ClientService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {

        Client client = service.findById(id);
        return ResponseEntity.ok().body(client);
    }
}
