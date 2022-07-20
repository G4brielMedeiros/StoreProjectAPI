package dev.gabriel.storeproject.controller;

import dev.gabriel.storeproject.domain.Client;
import dev.gabriel.storeproject.dto.ClientDTO;
import dev.gabriel.storeproject.dto.NewClientDTO;
import dev.gabriel.storeproject.service.entity.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clients")
@RequiredArgsConstructor
public class ClientController {

    final ClientService service;

    @PostMapping
    public ResponseEntity<Void> create( @Valid @RequestBody NewClientDTO dto) {
        Client client = service.add(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(client.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable Integer id) {
        Client client = service.findById(id);
        return ResponseEntity.ok(client);
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll() {
        List<ClientDTO> clientList = service.findAll().stream().map(ClientDTO::new).toList();
        return ResponseEntity.ok(clientList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ClientDTO dto, @PathVariable Integer id) {
        dto.setId(id);
        service.update(dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ClientDTO>> findPage(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "24") Integer size,
            @RequestParam(defaultValue = "name") String orderBy,
            @RequestParam(defaultValue = "ASC") String direction
    ) {
        Page<ClientDTO> dtoPage = service.findPage(page, size, orderBy, direction);
        return ResponseEntity.ok(dtoPage);
    }
}
