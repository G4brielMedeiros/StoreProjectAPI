package dev.gabriel.storeproject.service.validation;

import dev.gabriel.storeproject.controller.exception.FieldMessage;
import dev.gabriel.storeproject.domain.Client;
import dev.gabriel.storeproject.domain.enums.ClientType;
import dev.gabriel.storeproject.dto.ClientDTO;
import dev.gabriel.storeproject.dto.NewClientDTO;
import dev.gabriel.storeproject.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDTO> {

    final ClientRepository clientRepository;
    final HttpServletRequest request;

    @Override
    public void initialize(ClientUpdate ann) {}

    @Override
    public boolean isValid(ClientDTO dto, ConstraintValidatorContext context) {

        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> fieldMessageList = new ArrayList<>();

        Optional<Client> client = clientRepository.findClientByEmail(dto.getEmail());

        if(client.isPresent() && !client.get().getId().equals(uriId)) {
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
