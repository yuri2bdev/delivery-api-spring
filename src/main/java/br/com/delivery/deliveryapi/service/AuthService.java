package br.com.delivery.deliveryapi.service;


import br.com.delivery.deliveryapi.dto.RegisterRequest;
import br.com.delivery.deliveryapi.dto.AuthenticationResponse;
import br.com.delivery.deliveryapi.dto.LoginRequest;
import br.com.delivery.deliveryapi.model.Usuario;
import br.com.delivery.deliveryapi.repository.UsuarioRepository;
import br.com.delivery.deliveryapi.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;


@Service
public class AuthService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;

    public void signup(@RequestBody RegisterRequest registerRequest) {
        Usuario user = new Usuario();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(this.passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        user.setTelefone(registerRequest.getTelefone());
        userRepository.save(user);
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String authenticationToken = jwtProvider.generateToken(authenticate);
        Optional<Usuario> userOptional = Optional.ofNullable(this.userRepository.findByUsername(loginRequest.getUsername()));
        Usuario user = userOptional.orElseThrow(() -> new UsernameNotFoundException("Nenhum usuário encontrado para o login: " + loginRequest.getUsername()));
        return new AuthenticationResponse(authenticationToken, user.getUsername());
    }

    public Optional<Usuario> getUserByUsername(String username) {
        return Optional.ofNullable(this.userRepository.findByUsername(username));
    }
}
