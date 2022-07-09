package dev.gabriel.storeproject.service;

import dev.gabriel.storeproject.domain.Client;
import dev.gabriel.storeproject.repository.ClientRepository;
import dev.gabriel.storeproject.service.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService implements EntityService<Client>{

    final ClientRepository repository;

    public Client findById(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("" + Client.class.getSimpleName() + " not found. Id: " + id));
    }


}
