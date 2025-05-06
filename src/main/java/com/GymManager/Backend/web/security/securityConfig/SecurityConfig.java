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
                    request.requestMatchers( "/api/inventory/**").permitAll();
                    request.requestMatchers( "/api/Eventos/**").permitAll();
                    request.requestMatchers( "/api/contact/**").permitAll();
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

/*
* del front me llega un email, ese email lo busco en la base de datos  y traigo al usuario que le correspe, me trae el usuario que es ya que mis usuarios no pueden tener el mismo correo, luego, luego, creo un id ramdom muy largo, y luego lo guardo en un registro
* en mi db junto con el email del usuario , su token y la fecha de expiracion, luego de tener eso creado lo envio al correo del usuario, y luego de eso eso pued ya recibo ese token, lo busco en la base de datos, si esta y es valido
* (la fecha) entonces en la base de datos cambio la contraseña, pero antes de eso la encrypto la que me llego del front y listo, devuelvo un ok y ya el fronmt lo lee y que lo dirija al /login
*
*
*
* */



