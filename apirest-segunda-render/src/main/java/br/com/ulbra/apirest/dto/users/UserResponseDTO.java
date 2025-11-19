package br.com.ulbra.apirest.dto.users;

import java.util.ArrayList;
import java.util.List;

public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private List<UserPostDTO> posts = new ArrayList<>();

    public UserResponseDTO(Long id, String name, String email, List<UserPostDTO> posts) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.posts = posts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<UserPostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<UserPostDTO> posts) {
        this.posts = posts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
