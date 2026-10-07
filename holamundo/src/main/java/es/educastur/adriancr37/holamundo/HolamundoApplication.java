package es.educastur.adriancr37.holamundo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HolamundoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HolamundoApplication.class, args);
    }

    @Bean
    CommandLineRunner saludar() {
        return args -> System.out.println("¡Hola Mundo desde Spring Boot!");
    }
}