package dev.gabriel.storeproject.service;

import dev.gabriel.storeproject.domain.Address;
import dev.gabriel.storeproject.domain.Category;
import dev.gabriel.storeproject.domain.City;
import dev.gabriel.storeproject.domain.Client;
import dev.gabriel.storeproject.domain.enums.ClientType;
import dev.gabriel.storeproject.dto.CategoryDTO;
import dev.gabriel.storeproject.dto.ClientDTO;
import dev.gabriel.storeproject.dto.NewClientDTO;
import dev.gabriel.storeproject.repository.CityRepository;
import dev.gabriel.storeproject.repository.ClientRepository;
import dev.gabriel.storeproject.service.exception.DataIntegrityException;
import dev.gabriel.storeproject.service.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService implements EntityService<Client> {

    final ClientRepository repository;
    final CityRepository cityRepository;

    public List<Client> findAll() {
        return repository.findAll();
    }

    public Client findById(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("" + Client.class.getSimpleName() + " not found. Id: " + id));
    }

    public Client add(NewClientDTO dto) {
        Client client = new Client(
                dto.getName(),
                dto.getEmail(),
                dto.getGovernmentRegistration(),
                ClientType.toEnum(dto.getType())
        );

        City city = cityRepository.findById(dto.getCityId()).orElseThrow(
                () -> new ObjectNotFoundException(City.class.getSimpleName() + " not found. Id: " + dto.getCityId()));

        Address address = new Address(
                dto.getStreet(),
                dto.getNumber(),
                dto.getComplement(),
                dto.getPostalCode(),
                client,
                city
        );

        client.getAddresses().add(address);

        client.getPhoneNumbers().add(dto.getPhoneNumber1());

        if (dto.getPhoneNumber2() != null) {
            client.getPhoneNumbers().add(dto.getPhoneNumber2());
        }

        if (dto.getPhoneNumber3() != null) {
            client.getPhoneNumbers().add(dto.getPhoneNumber3());
        }

        return repository.save(client);
    }

    public void update(ClientDTO dto) {
        Client client = findById(dto.getId());
        BeanUtils.copyProperties(dto, client);
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

        Page<Client> clientList = repository.findAll(pageRequest);
        return clientList.map(ClientDTO::new);
    }
}
