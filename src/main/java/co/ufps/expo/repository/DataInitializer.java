package co.ufps.expo.repository;

import co.ufps.expo.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final UsuarioRepository usuarioRepository;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            Usuario usuario1 = new Usuario(null, "Usuario 1", "usuario1@example.com");
            Usuario usuario2 = new Usuario(null, "Usuario 2", "usuario2@example.com");

            usuarioRepository.saveAll(Arrays.asList(usuario1, usuario2));
        };
    }
}
