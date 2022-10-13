package ro.sda.finalproject.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ro.sda.finalproject.backend.mapper.ProductsMapper;

@Configuration
public class BeanConfig {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
    @Bean
    public ProductsMapper createProductsMapper(){
        return new ProductsMapper();
}

}

