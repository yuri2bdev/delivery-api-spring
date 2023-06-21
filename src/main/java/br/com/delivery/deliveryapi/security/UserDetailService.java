package br.com.delivery.deliveryapi.security;

import br.com.delivery.deliveryapi.model.Usuario;
import br.com.delivery.deliveryapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UsuarioRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> optionalUser = Optional.ofNullable(this.userRepository.findByUsername(username));
        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("No User Found: " + username);
        }
        Usuario user = optionalUser.get();
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), true, true,
                true, true, getAuthorities()
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

}
