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
                    // Auth
                    request.requestMatchers(HttpMethod.POST, "/api/auth/signIn").permitAll();
                    request.requestMatchers(HttpMethod.GET, "/api/auth/validate/*").permitAll();
                    request.requestMatchers(HttpMethod.GET, "/api/auth/generateResetPasswordToken/**").permitAll();
                    request.requestMatchers(HttpMethod.POST, "/api/auth/signWithGoogle").permitAll();
                    request.requestMatchers(HttpMethod.POST, "/api/user/save").permitAll();
                    request.requestMatchers(HttpMethod.POST, "/api/auth/resetPassword").permitAll();
                    request.requestMatchers(HttpMethod.POST, "/api/auth/resetPassword/profile").authenticated();
                    request.requestMatchers(HttpMethod.GET, "/api/auth/isValidTokenResetPassword/*").permitAll();

                    //events

                    request.requestMatchers("/api/Eventos/**").permitAll();


                    // member
                    request.requestMatchers(HttpMethod.GET, "/api/membership/public").permitAll();
                    request.requestMatchers("/api/correo/send").permitAll();
                    request.requestMatchers(HttpMethod.GET, "/api/members/**").hasAnyRole(ROLE_ADMIN, ROLE_SUPERADMIN);
                     request.requestMatchers(HttpMethod.POST, "/api/members/**").hasAnyRole(ROLE_ADMIN, ROLE_SUPERADMIN);
                    request.requestMatchers(HttpMethod.PUT, "/api/members/**").hasAnyRole(ROLE_ADMIN, ROLE_SUPERADMIN);
                    request.requestMatchers(HttpMethod.DELETE, "/api/members/**").hasAnyRole(ROLE_ADMIN, ROLE_SUPERADMIN);

                    // dashboard
                    request.requestMatchers( "/api/dashboard/**").hasAnyRole(ROLE_ADMIN, ROLE_SUPERADMIN);

                    // inventory
                    request.requestMatchers("/api/inventory/**").hasAnyRole(ROLE_ADMIN, ROLE_SUPERADMIN);

                    // memberships
                    request.requestMatchers("/api/membership/**").hasAnyRole(ROLE_ADMIN, ROLE_SUPERADMIN);
                    request.requestMatchers(HttpMethod.GET, "/api/membership/public").permitAll();

                    // visits
                    request.requestMatchers("/api/visits/**").hasAnyRole(ROLE_ADMIN, ROLE_SUPERADMIN);

                    // activities
                    request.requestMatchers("/api/activity/**").hasAnyRole(ROLE_ADMIN, ROLE_SUPERADMIN);

                    // sales

                    request.requestMatchers( "/api/members/**").hasAnyRole(ROLE_ADMIN,ROLE_SUPERADMIN);
                    request.requestMatchers( "/api/membership/**").hasAnyRole(ROLE_ADMIN, ROLE_SUPERADMIN);
                    request.requestMatchers( "/api/visits/**").hasAnyRole(ROLE_ADMIN, ROLE_SUPERADMIN);
                    request.requestMatchers( "/api/activity/**").hasAnyRole(ROLE_ADMIN, ROLE_SUPERADMIN);
                    request.requestMatchers("/api/profile/**").permitAll();

                    request.requestMatchers("/api/sales/**").hasAnyRole(ROLE_ADMIN, ROLE_SUPERADMIN);

                    // subscription
                    request.requestMatchers("/api/subscription/request/status/subscription/**").permitAll();
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





