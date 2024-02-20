package com.example.springboot3jpa.infrastructure.authentication;

import com.example.springboot3jpa.domain.shared.MemberRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //암호화 기능
    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
                        .requestMatchers("/", "/login", "/join", "/sign-in", "/sign-up", "/home").permitAll());

        http
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
                        .requestMatchers("/seller").hasRole(MemberRole.SELLER.name())
                        .anyRequest().authenticated()

                ).formLogin((auth) ->
                        auth.loginPage("/login")  // 커스텀 로그인 페이지를 설정
                                .loginProcessingUrl("/sign-in")
                                .usernameParameter("loginId")
                                .passwordParameter("password")
                                .permitAll().defaultSuccessUrl("/home"))
                .logout(auth -> auth.logoutUrl("/logout")) // logout시
                .httpBasic(Customizer.withDefaults())
//                .csrf((auth) -> auth.disable())
                .sessionManagement((auth) -> auth.maximumSessions(1) // 다중로그인 개수 1개
                        .maxSessionsPreventsLogin(true) // 초과시 새로운 로그인 차단
                ).sessionManagement((auth) -> auth.sessionFixation().changeSessionId());

        // api token으로
//        http
//                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
//                        .requestMatchers("/api/**").permitAll()
//                        .anyRequest().authenticated())
//                .httpBasic(Customizer.withDefaults())
//                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // session을 사용하지 않음
//                )
//                .addFilterBefore(new AuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
