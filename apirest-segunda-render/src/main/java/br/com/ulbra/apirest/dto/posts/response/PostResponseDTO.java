package br.com.ulbra.apirest.dto.posts.response;

public class PostResponseDTO {
    private Long id;
    private String content;
    private PostUserDTO user;

    public PostResponseDTO() {}

    public PostResponseDTO(Long id, String content, PostUserDTO user) {
        this.id = id;
        this.content = content;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PostUserDTO getUser() {
        return user;
    }

    public void setUser(PostUserDTO user) {
        this.user = user;
    }

    public record MessageDTO(String message) {}
}
