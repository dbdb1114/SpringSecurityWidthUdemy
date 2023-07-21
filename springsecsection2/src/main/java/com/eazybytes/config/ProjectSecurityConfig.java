package com.eazybytes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {


        /**
         * 아래에 권한이 필요한 url을 입력해준다.
         * */
        http.authorizeHttpRequests((requests) -> {
            requests.requestMatchers("/myAccount","/myBalance","/myLoans","/myCards")
                    .authenticated()
                    .requestMatchers("/notices","/contact")
                    .permitAll();
        });
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();


        /**
         *
         *  configuration to denay all the requests
         * */

//        http.authorizeHttpRequests((requests) -> {
//            requests.anyRequest().denyAll();
//        });
//        http.formLogin(withDefaults());
//        http.httpBasic(withDefaults());
//        return http.build();


        /**
         *  configuration to permit all the requests
         * */
//        http.authorizeHttpRequests((requests) -> {
//            requests.anyRequest().permitAll();
//        });
//        http.formLogin(withDefaults());
//        http.httpBasic(withDefaults());
//        return http.build();


    }
}
