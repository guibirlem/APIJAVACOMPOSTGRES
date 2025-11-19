package br.com.ulbra.apirest.services;

import br.com.ulbra.apirest.dto.users.UserPostDTO;
import br.com.ulbra.apirest.dto.users.UserResponseDTO;
import br.com.ulbra.apirest.entities.User;
import br.com.ulbra.apirest.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(Long id) {
        return this.userRepository.findById(id).orElseThrow();
    }

    public Page<UserResponseDTO> getUsers(Pageable pageable) {
        return this.userRepository.findAll(pageable)
                .map(item -> new UserResponseDTO(
                        item.getId(),
                        item.getName(),
                        item.getEmail(),
                        item.getPosts().stream().map(
                                posts -> new UserPostDTO(posts.getContent())
                        ).toList()
                ));
    }

    public User createUser(User user) {
        return this.userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = this.userRepository.findById(id).orElseThrow();
        this.userRepository.delete(user);
    }

    public User updateUser(Long id, User updatedData) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        existingUser.setName(updatedData.getName());
        existingUser.setEmail(updatedData.getEmail());

        if (updatedData.getPassword() != null && !updatedData.getPassword().isEmpty()) {
            existingUser.setPassword(updatedData.getPassword());
        }

        return userRepository.save(existingUser);
    }
}
