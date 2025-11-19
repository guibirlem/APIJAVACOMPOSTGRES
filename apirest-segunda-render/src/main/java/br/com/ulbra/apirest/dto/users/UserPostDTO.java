package br.com.ulbra.apirest.dto.users;

public class UserPostDTO {
    private String content;

    public UserPostDTO(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
