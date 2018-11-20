package com.ebay.jimo.tacocloud.security;

import com.ebay.jimo.tacocloud.data.UserRepository;
import com.ebay.jimo.tacocloud.domain.User;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {
    private UserRepository userRepo;

    @Autowired
    public UserRepositoryUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(final String s) throws UsernameNotFoundException {

        User user = userRepo.findByUsername(s);
        if (Objects.nonNull(user)) {
            return user;
        } else {
            throw new UsernameNotFoundException("User '" + s + "' not found");
        }
    }
}
