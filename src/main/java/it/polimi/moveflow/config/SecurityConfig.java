package it.polimi.moveflow.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.awt.desktop.SystemSleepEvent;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**").permitAll()

                        .requestMatchers("/ubicazioni/**", "/utenti/**")
                        .hasRole("ADMIN")

                        .requestMatchers("/materiali/**",
                                         "/magazzino/**",
                                          "/etichette/**")
                        .hasAnyRole("ADMIN","OPERATORE")

                        .requestMatchers("/dashboard/**",
                                          "/movimenti/**")
                        .hasAnyRole("ADMIN","RESPONSABILE")

                        .anyRequest().authenticated()
                )
                .formLogin( form -> form
                        .defaultSuccessUrl("/",true)
                        .permitAll()
                )
                .exceptionHandling(ex -> ex
                        .accessDeniedPage("/accesso-negato"))
                .logout(logout -> logout
                        .permitAll()
                );

       //  BCryptPasswordEncoder ecnoder = new BCryptPasswordEncoder();
       // System.out.println(ecnoder.encode("responsabile123"));

        return http.build();

    }
}
