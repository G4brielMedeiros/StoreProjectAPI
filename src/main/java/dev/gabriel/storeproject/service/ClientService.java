package dev.gabriel.storeproject.service;

import dev.gabriel.storeproject.domain.Client;
import dev.gabriel.storeproject.dto.ClientDTO;
import dev.gabriel.storeproject.repository.ClientRepository;
import dev.gabriel.storeproject.service.exception.DataIntegrityException;
import dev.gabriel.storeproject.service.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService implements EntityService<Client> {

    final ClientRepository repository;

    public List<Client> findAll() {
        return repository.findAll();
    }

    public Client findById(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("" + Client.class.getSimpleName() + " not found. Id: " + id));
    }

    public void update(ClientDTO dto) {
        Client client = findById(dto.getId());
        client.setName(dto.getName());
        client.setEmail(dto.getEmail());
        repository.save(client);
    }

    public void deleteById(Integer id) {
        findById(id);

        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException exception) {
            throw new DataIntegrityException("Cannot delete a client that has related entities.");
        }
    }

    public Page<ClientDTO> findPage(Integer page, Integer size, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);

        Page<Client> clientList =  repository.findAll(pageRequest);
        return clientList.map(ClientDTO::new);
    }
}
