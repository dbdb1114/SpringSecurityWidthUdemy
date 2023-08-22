package com.eazybytes.config;

import com.eazybytes.filter.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;


import javax.sql.DataSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Supplier;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class ProjectSecurityConfig {



    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        /**
         * csrf 토큰핸들러
         * */
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");



        /**
         * csrf 공격 방지
         * withHttpOnlyFalse : csrf cookie를 httpOnlyFalse로 생성하는 것.
         * 아래 설정으로 csrf 토큰을 쿠키에 저장하는 것과 insert 나 update가 아닌 다른 요청들은 허용하게 됨.
         * BasicAuthenticationFilter 다음에 실행시켜줌으로써 로그인이 된 상황일 것임.
         * */
        http.csrf(csrfConfigurer->{
            csrfConfigurer.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers( "/register")
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());})
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
                .addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class);
        /**
         * spring security가 jsessionID나 httpSession을 만들지 말고 모든것을 알아서 만들겠다는 설
         * */
        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(httpSecurityCorsConfigurer -> {
                    httpSecurityCorsConfigurer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        /** Jwt 토큰을 노출시키기 위한 설정. 이런 설정 없으면 ftont에서 jwt토큰을 볼 수 없음.*/
                        config.setExposedHeaders(Arrays.asList("Authorization"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                });
            });

        /**
         * 아래에 권한이 필요한 url을 입력해준다.
         * */
        http.authorizeHttpRequests((requests) -> {
            requests
//                    .requestMatchers("/myAccount").hasAuthority("VIEWACCOUNT")
//                    .requestMatchers("/myBalance").hasAnyAuthority("VIEWACCOUNT","VIEWBALANCE")
//                    .requestMatchers("/myLoans").hasAuthority("VIEWLOANS")
//                    .requestMatchers("/myCards").hasAuthority("VIEWCARDSDETAILS")
                    /**
                     * 원래 ROLE_USER 이런 방식을 써주는게 정석이지만, 안 써있으면 내부적으로 알아서 ROLE_을 붙여줌.
                     * */
                    .requestMatchers("/myAccount").hasRole("USER")
                    .requestMatchers("/myBalance").hasAnyRole("USER","ADMIN")
                    .requestMatchers("/myLoans").hasRole("USER")
                    .requestMatchers("/myCards").hasRole("USER")

                    .requestMatchers("/user").authenticated()
                    .requestMatchers("/notices", "/contact","/register").permitAll();
        });
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
