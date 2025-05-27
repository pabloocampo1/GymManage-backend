package com.GymManager.Backend.web.security.securityConfig;


import com.GymManager.Backend.web.security.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    @Autowired
    private CorsConfig corsConfig;

    private final String ROLE_ADMIN = "ADMIN";
    private final String ROLE_SUPERADMIN = "SUPERADMIN";

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(this.corsConfig.corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sess -> {
                    sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .authorizeHttpRequests(request -> {
                    request.requestMatchers(HttpMethod.POST, "/api/auth/signIn").permitAll();
                    request.requestMatchers(HttpMethod.GET, "/api/auth/validate/*").permitAll();
                    request.requestMatchers(HttpMethod.GET, "/api/auth/generateResetPasswordToken/**").permitAll();
                    request.requestMatchers(HttpMethod.POST, "/api/user/save").permitAll();
                    request.requestMatchers(HttpMethod.POST, "/api/auth/resetPassword").permitAll();
                    request.requestMatchers(HttpMethod.GET, "/api/auth/isValidTokenResetPassword/*").permitAll();
                    request.requestMatchers( "/api/inventory/**").hasRole(ROLE_ADMIN);
                    request.requestMatchers( "/api/Eventos/**").hasRole(ROLE_ADMIN);
                    request.requestMatchers( "/api/miembros/**").hasRole(ROLE_ADMIN);
                    request.requestMatchers( "/api/membresias/**").hasRole(ROLE_ADMIN);
                    request.anyRequest().authenticated();
                })
                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager (AuthenticationConfiguration config) throws Exception {
            return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

}





