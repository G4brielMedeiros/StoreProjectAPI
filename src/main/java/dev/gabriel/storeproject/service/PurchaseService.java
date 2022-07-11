package dev.gabriel.storeproject.service;

import dev.gabriel.storeproject.domain.Purchase;
import dev.gabriel.storeproject.repository.PurchaseRepository;
import dev.gabriel.storeproject.service.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseService implements EntityService<Purchase>{

    final PurchaseRepository repository;

    public Purchase findById(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("" + Purchase.class.getSimpleName() + " not found. Id: " + id));
    }


}
