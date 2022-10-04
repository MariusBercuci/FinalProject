package ro.sda.finalproject.backend.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BeanConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//                .authorizeRequests((authz) -> authz.antMatchers(HttpMethod.GET, "/api/cars").hasAnyRole("ADMIN", "CARS")
////                .antMatchers(HttpMethod.POST, "/api/author").authenticated()
////                .antMatchers("/api/users/**").hasAuthority("ROLE_USER_ADMIN")
//                        .anyRequest().permitAll() )
                .formLogin().and()
                .httpBasic().and()
                .logout()
                .and()
                .csrf().ignoringAntMatchers("/api/**")
                .and()
                .headers().frameOptions().disable()
        ;
        return http.build();
    }
}