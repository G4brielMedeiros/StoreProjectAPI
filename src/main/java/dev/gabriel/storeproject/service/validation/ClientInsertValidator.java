package dev.gabriel.storeproject.service.validation;

import dev.gabriel.storeproject.controller.exception.FieldMessage;
import dev.gabriel.storeproject.domain.Client;
import dev.gabriel.storeproject.domain.enums.ClientType;
import dev.gabriel.storeproject.dto.NewClientDTO;
import dev.gabriel.storeproject.repository.ClientRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ClientInsertValidator implements ConstraintValidator<ClientInsert, NewClientDTO> {

    final ClientRepository clientRepository;
    @Override
    public void initialize(ClientInsert ann) {}

    @Override
    public boolean isValid(NewClientDTO dto, ConstraintValidatorContext context) {
        List<FieldMessage> fieldMessageList = new ArrayList<>();

        if (dto.getType() != null && dto.getType().equals(ClientType.PERSON.getCode()) && !Brazil.isValidCPF(dto.getGovernmentRegistration())) {
            fieldMessageList.add(new FieldMessage("governmentRegistration", "Invalid CPF"));
        }

        if (dto.getType() != null && dto.getType().equals(ClientType.BUSINESS.getCode()) && !Brazil.isValidCNPJ(dto.getGovernmentRegistration())) {
            fieldMessageList.add(new FieldMessage("governmentRegistration", "Invalid CNPJ"));
        }

        Optional<Client> client = clientRepository.findClientByEmail(dto.getEmail());

        if(client.isPresent()) {
            fieldMessageList.add(new FieldMessage("email", "Email already in use."));
        }

        fieldMessageList.forEach(fieldMessage -> {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(fieldMessage.getMessage())
                    .addPropertyNode(fieldMessage.getFieldName())
                    .addConstraintViolation();
        });

        return fieldMessageList.isEmpty();
    }
}
