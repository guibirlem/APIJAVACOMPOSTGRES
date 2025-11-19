package br.com.ulbra.apirest.controllers;

import br.com.ulbra.apirest.dto.users.UserResponseDTO;
import br.com.ulbra.apirest.entities.User;
import br.com.ulbra.apirest.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getUsers(Pageable pageable) {
        Page<UserResponseDTO> page = this.userService.getUsers(pageable);
        return ResponseEntity.ok(page.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(this.userService.getUser(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = this.userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(this.userService.createUser(user));
    }

        @DeleteMapping("/{id}")
         public ResponseEntity<String> deleteUser(@PathVariable Long id) {
         this.userService.deleteUser(id);
         return ResponseEntity.ok("Usuário deletado");
    }

}
