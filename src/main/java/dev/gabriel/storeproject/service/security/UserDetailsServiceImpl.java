package dev.gabriel.storeproject.service.security;

import dev.gabriel.storeproject.domain.Client;
import dev.gabriel.storeproject.repository.ClientRepository;
import dev.gabriel.storeproject.security.UserSS;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    final ClientRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Client client = repository.findClientByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return new UserSS(client.getId(), client.getEmail(), client.getPassword(), client.getProfiles());
    }
}
