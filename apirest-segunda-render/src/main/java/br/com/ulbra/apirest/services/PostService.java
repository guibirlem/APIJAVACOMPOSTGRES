package br.com.ulbra.apirest.services;

import br.com.ulbra.apirest.dto.MessageDTO;
import br.com.ulbra.apirest.dto.posts.request.PostRequest;
import br.com.ulbra.apirest.dto.posts.response.PostResponseDTO;
import br.com.ulbra.apirest.dto.posts.response.PostUserDTO;
import br.com.ulbra.apirest.entities.Post;
import br.com.ulbra.apirest.entities.User;
import br.com.ulbra.apirest.repositories.PostRepository;
import br.com.ulbra.apirest.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<PostResponseDTO> getAllPosts() {
        return this.postRepository.findAll()
                .stream()
                .map(item ->
                        new PostResponseDTO(
                                item.getId(),
                                item.getContent(),
                                new PostUserDTO(
                                        item.getUser().getId(),
                                        item.getUser().getName()
                                )
                        )).toList();
    }

    public PostResponseDTO getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        return new PostResponseDTO(
                post.getId(),
                post.getContent(),
                new PostUserDTO(
                        post.getUser().getId(),
                        post.getUser().getName()
                )
        );
    }

    public Post createPost(PostRequest postRequest) {
        User user = userRepository.findById(postRequest.getUserId()).orElseThrow();

        Post post = new Post();
        post.setContent(postRequest.getContent());
        post.setUser(user);

        return postRepository.save(post);
    }

    public MessageDTO deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return new MessageDTO("Post deletado com sucesso");
    }

    public PostResponseDTO updatePost(Long id, PostRequest postRequest) {
        Post post = postRepository.findById(id).orElseThrow();

        post.setContent(postRequest.getContent());

        if (postRequest.getUserId() != null) {
            User user = userRepository.findById(postRequest.getUserId()).orElseThrow();
            post.setUser(user);
        }

        Post updatedPost = postRepository.save(post);

        return new PostResponseDTO(
                updatedPost.getId(),
                updatedPost.getContent(),
                new PostUserDTO(
                        updatedPost.getUser().getId(),
                        updatedPost.getUser().getName()
                )
        );
    }
}
