package br.com.ulbra.apirest.services;

import br.com.ulbra.apirest.entities.Usuario;
import br.com.ulbra.apirest.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UsuarioRepository userRepository;

    public UserService(UsuarioRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Usuario> getAllUsers() {
        return userRepository.findAll();
    }

    public Usuario getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + id));
    }

    public Usuario createUser(Usuario user) {
        return userRepository.save(user);
    }

    public Usuario updateUser(Long id, Usuario user) {
        Usuario existing = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + id));
        existing.setNome(user.getNome());
        existing.setEmail(user.getEmail());
        return userRepository.save(existing);
    }

    public void deleteUser(Long id) {
        Usuario user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + id));
        userRepository.delete(user);
    }
}
