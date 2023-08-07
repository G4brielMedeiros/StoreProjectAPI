package dev.gabriel.storeproject.service;

import dev.gabriel.storeproject.domain.Client;
import dev.gabriel.storeproject.repository.ClientRepository;
import dev.gabriel.storeproject.service.email.EmailService;
import dev.gabriel.storeproject.service.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class AuthService {

    final ClientRepository repository;
    final BCryptPasswordEncoder encoder;
    final EmailService emailService;
    private final Random random = new Random();

    public void sendNewPassword(String email) {
        Client client = repository.findClientByEmail(email).orElseThrow(() -> new ObjectNotFoundException("Email not found."));
        String newPassword = newPassword();
        client.setPassword(encoder.encode(newPassword));
        repository.save(client);
        emailService.sendNewPasswordEmail(client, newPassword);
    }

    private String newPassword() {
        char[] chars = new char[16];
        IntStream.range(0, 16).forEach(value -> chars[value] = randomChar());
        return new String(chars);
    }

    private char randomChar() {
        int opt = random.nextInt(3);
        if (opt == 0) return (char) (random.nextInt(10) + 48); // digit
        if (opt == 1) return (char) (random.nextInt(26) + 65); // lowercase
        return (char) (random.nextInt(26) + 97);               // uppercase
    }
}
