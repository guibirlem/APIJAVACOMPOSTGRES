package br.com.ulbra.apirest.dto.posts.response;

public class PostResponseDTO {
    private Long id;
    private String descricao;
    private PostUserDTO usuario;

    public PostResponseDTO() {}

    public PostResponseDTO(Long id, String descricao, PostUserDTO usuario) {
        this.id = id;
        this.descricao = descricao;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public PostUserDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(PostUserDTO usuario) {
        this.usuario = usuario;
    }

    public static class PostUserDTO {
        private Long id;
        private String nome;

        public PostUserDTO() {}

        public PostUserDTO(Long id, String nome) {
            this.id = id;
            this.nome = nome;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }
    }
}
