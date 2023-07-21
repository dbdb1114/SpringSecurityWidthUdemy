package com.eazybytes.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrfConfigurer->csrfConfigurer.disable());
        http.cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.disable());
        /**
         * 아래에 권한이 필요한 url을 입력해준다.
         * */
        http.authorizeHttpRequests((requests) -> {
            requests.requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards")
                    .authenticated()
                    .requestMatchers("/notices", "/contact","/register")
                    .permitAll();
        });
            http.formLogin(withDefaults());
            http.httpBasic(withDefaults());
        return http.build();

    }

//    @Bean
//    public InMemoryUserDetailsManager userDeatilsService(){

//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("12345")
//                .authorities("admin")
//                .build();
//
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("12345")
//                .authorities("read")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin,user);


        /**
         * 패스워드 인코더를 분리하여 사용하는 방법
         * PasswordEncder를 사용하지 않을 때는 Bean을 하나 더 선언해줘야함.
         * */
//        UserDetails admin = User.withUsername("admin")
//                .password("12345")
//                .authorities("admin")
//                .build();
//
//        UserDetails user = User.withUsername("user")
//                .password("12345")
//                .authorities("read")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin,user);
//    }

    /**
     * JDBCUserDetailsManager는 작은 프로젝트나 테스트용 프로젝트에 적합하다.
     * 이유는 내장되어 있는 sql 구문들이 실제 요구사항에 따른 스키마와과 차이가 클 수 있는데
     * JdbcUserDetailsManager는 final로 sql 구문이 지정되어 있어 변경이 불가하기 때문이다.
     *
     * 내장되어 있는 JdbcUserDetailsManager는 사실상 레퍼런스로서 적용되어 있을 가능성이 높다.
     *
     * EazyBankUserDetails를 만들었으니 해당 내용을 주석처리한다.
     * UserDetailsManager를 여러개 만들어놓을 경우 spring은 처리를 하지 못한다.
     * */
//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        System.out.println(NoOpPasswordEncoder.getInstance());
        return NoOpPasswordEncoder.getInstance();
    }

}
