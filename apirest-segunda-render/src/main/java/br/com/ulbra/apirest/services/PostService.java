package br.com.ulbra.apirest.services;

import br.com.ulbra.apirest.dto.posts.request.PostRequest;
import br.com.ulbra.apirest.dto.posts.response.PostResponseDTO;
import br.com.ulbra.apirest.dto.posts.response.PostResponseDTO.PostUserDTO;
import br.com.ulbra.apirest.dto.MessageDTO;
import br.com.ulbra.apirest.entities.Post;
import br.com.ulbra.apirest.entities.Usuario;
import br.com.ulbra.apirest.repositories.PostRepository;
import br.com.ulbra.apirest.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UsuarioRepository usuarioRepository;

    public PostService(PostRepository postRepository, UsuarioRepository usuarioRepository) {
        this.postRepository = postRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<PostResponseDTO> getAllPosts() {
        return this.postRepository.findAll()
                .stream()
                .map(item -> new PostResponseDTO(
                        item.getId(),
                        item.getDescricao(),
                        new PostUserDTO(item.getUsuario().getId(), item.getUsuario().getNome())
                ))
                .toList();
    }

    public PostResponseDTO getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        return new PostResponseDTO(
                post.getId(),
                post.getDescricao(),
                new PostUserDTO(post.getUsuario().getId(), post.getUsuario().getNome())
        );
    }

    public PostResponseDTO createPost(PostRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId()).orElseThrow();

        Post post = new Post();
        post.setDescricao(request.getDescricao());
        post.setUsuario(usuario);

        Post saved = postRepository.save(post);

        return new PostResponseDTO(
                saved.getId(),
                saved.getDescricao(),
                new PostUserDTO(saved.getUsuario().getId(), saved.getUsuario().getNome())
        );
    }

    public MessageDTO deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return new MessageDTO("Post deletado com sucesso");
    }

    public PostResponseDTO updatePost(Long id, PostRequest request) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setDescricao(request.getDescricao());

        if (request.getUsuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(request.getUsuarioId()).orElseThrow();
            post.setUsuario(usuario);
        }

        Post updated = postRepository.save(post);
        return new PostResponseDTO(
                updated.getId(),
                updated.getDescricao(),
                new PostUserDTO(updated.getUsuario().getId(), updated.getUsuario().getNome())
        );
    }
}
