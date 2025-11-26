package br.com.ulbra.apirest.controllers;

import br.com.ulbra.apirest.entities.Usuario;
import br.com.ulbra.apirest.services.UserService;
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
    public ResponseEntity<List<Usuario>> getUsers() {
        System.out.println("GET /users");
        List<Usuario> users = this.userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUser(@PathVariable Long id) {
        System.out.println("GET /users/" + id);
        return ResponseEntity.ok(this.userService.getUser(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUser(@PathVariable Long id, @RequestBody Usuario user) {
        System.out.println("PUT /users/" + id);
        Usuario updatedUser = this.userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping
    public ResponseEntity<Usuario> createUser(@RequestBody Usuario user) {
        System.out.println("POST /users");
        Usuario createdUser = this.userService.createUser(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdUser.getId()).toUri();

        return ResponseEntity.created(uri).body(createdUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        System.out.println("DELETE /users/" + id);
        this.userService.deleteUser(id);
        return ResponseEntity.ok("Usuário deletado");
    }
}
