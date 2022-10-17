package ro.sda.finalproject.backend.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ro.sda.finalproject.backend.services.AppUserServices;


import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@Data
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {
    public SecurityConfiguration(AppUserServices appUserServices, PasswordEncoder passwordEncoder, @Qualifier("appUserDetailsService") UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserServices = appUserServices;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    private AppUserServices appUserServices;
    private PasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;


//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.userDetailsService(userDetailsService);
//        http.csrf().disable();
//        http.cors().disable();
//        //http.authorizeRequests()
//        //.antMatchers(HttpMethod.DELETE,"/api/users").hasRole("ADMIN")
//
//        //.anyRequest().authenticated();
//
//        http.httpBasic()
//                .and()
//                .formLogin();
//
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService);

        // Here we pass in everything that we want Spring Security to manage in terms of endpoint or URL.
        // Which one to secure/leave open
        // First we tell to not use Cross Site Request Forgery because we are not using it
        // We add Cross Origin resource Sharing because we don't want anyone from any URL to be able to connect to our API
        // We want ony a specific list to be able to do that. Disabling it will allow anyone from any domain to send a request to our application
        // and the application will try to process it.
        http.csrf().disable().cors()
                .and()
                .sessionManagement().sessionCreationPolicy(STATELESS) // With JWT, we don't need to keep track of who is logged in, so we can set STATELESS instead of STATEFUL
                .and()
                .authorizeRequests().antMatchers("/api/**").permitAll().and() // provisory
                .authorizeRequests().antMatchers("/api/user/all", "/api/user/create", "/api/user/update").hasRole("ADMIN").and()
                .authorizeRequests().antMatchers("/api/user/login/**", "/api/user/create").permitAll() // We are telling the app the list of URLs that we are allowing the user to access without being authenticated
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedPage("/403");

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(appUserServices);
        return provider;
    }
}
