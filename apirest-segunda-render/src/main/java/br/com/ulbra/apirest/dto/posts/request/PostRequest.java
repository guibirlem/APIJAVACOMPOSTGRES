package br.com.ulbra.apirest.dto.posts.request;

public class PostRequest {
    private Long usuarioId;
    private String descricao;

    public PostRequest(){}

    public PostRequest(Long usuarioId, String descricao) {
        this.usuarioId = usuarioId;
        this.descricao = descricao;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
