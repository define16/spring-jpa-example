package com.example.springboot3jpa.infrastructure.authentication;

import com.example.springboot3jpa.domain.shared.MemberRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
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

                )
//                .formLogin((auth) ->
//                        auth.loginPage("/login")  // 커스텀 로그인 페이지를 설정
//                                .loginProcessingUrl("/sign-in")
//                                .usernameParameter("loginId")
//                                .passwordParameter("password")
//                                .permitAll().defaultSuccessUrl("/home"))
                .httpBasic(Customizer.withDefaults()) // http basic 방식으로 로그인 (팝업창)

                .logout(auth -> auth.logoutUrl("/logout")) // logout시
//                .csrf((auth) -> auth.disable())
                .sessionManagement((auth) -> auth.maximumSessions(1) // 다중로그인 개수 1개
                        .maxSessionsPreventsLogin(true) // 초과시 새로운 로그인 차단
                ).sessionManagement((auth) -> auth.sessionFixation().changeSessionId());

        return http.build();
    }

    @Bean
    public RoleHierarchy roleHierarchy() {

        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        hierarchy.setHierarchy(String.format("ROLE_%s > ROLE_%s\n" +
                "ROLE_%s > ROLE_%s",  MemberRole.ADMIN.name(),  MemberRole.SELLER.name(),
                MemberRole.ADMIN.name(),  MemberRole.USER.name()));

        return hierarchy;
    }

}
