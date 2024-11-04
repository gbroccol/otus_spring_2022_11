package ru.otus.spring.userdetails;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.spring.model.User;
import ru.otus.spring.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository authUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = authUserRepository.findByUsername(username)
                .orElseThrow( () -> new UsernameNotFoundException(username));
        return new UserDetailsImpl(user);
    }
}