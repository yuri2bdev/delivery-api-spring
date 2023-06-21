package br.com.delivery.deliveryapi.config;


import br.com.delivery.deliveryapi.security.JwtAuthenticationFilter;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    @Autowired
    @Qualifier("userDetailService")
    private UserDetailsService userDetailsService;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/auth/**", "/api/v1/usuarios/**")
                .permitAll()
                .antMatchers("/v2/api-docs/**", "/swagger-ui.html", "/swagger-ui/**",  "/swagger-resources/**", "/swagger-resources")
                .permitAll()
                .anyRequest()
                .authenticated();
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(@Qualifier("userDetailService") UserDetailsService userDetailsService) {
        var dao = new DaoAuthenticationProvider();
        dao.setUserDetailsService(userDetailsService);
        dao.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(dao);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}