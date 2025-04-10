package co.ufps.expo.service;

import co.ufps.expo.model.Usuario;
import co.ufps.expo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsuarios() { return usuarioRepository.findAll(); };

    public Optional<Usuario> getUsuarioById(Long id) { return usuarioRepository.findById(id); }

    public Usuario createUsuario(Usuario usuario) { return usuarioRepository.save(usuario); }

    public Usuario updateUsuario(Long id, Usuario usuarioDetails) {
        return usuarioRepository.findById(id).map( usuario ->{
            usuario.setNombre(usuarioDetails.getNombre());
            usuario.setEmail(usuarioDetails.getEmail());
            return usuarioRepository.save(usuario);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public void deleteUsuario(Long id) { usuarioRepository.deleteById(id);}
}
