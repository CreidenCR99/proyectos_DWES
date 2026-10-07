package es.educastur.adriancr37.holamundo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaMundoController {

    @GetMapping("/")
    public String saludar() {
        return "¡Hola Mundo desde Spring Boot!";
    }
}
