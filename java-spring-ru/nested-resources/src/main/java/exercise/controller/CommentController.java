package exercise.controller;

import exercise.ResourceNotFoundException;
import exercise.model.Comment;
import exercise.model.Post;
import exercise.repository.CommentRepository;
import exercise.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/posts")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    // BEGIN
//    GET /posts/{postId}/comments - вывод всех комментариев для конкретного поста.
//    Должны выводится только комментарии, принадлежащие посту.
    @GetMapping(path = "/{postId}/comments")
    public Iterable<Comment> getCommentsByPostId(@PathVariable long postId) {
        return commentRepository.findAllByPostId(postId);
    }

//    GET /posts/{postId}/comments/{commentId} - вывод конкретного комментария для поста.
//    Должны выводится только комментарий, принадлежащие посту. Если такой комментарий не существует, должен вернуться ответ с кодом 404.
    @GetMapping(path = "/{postId}/comments/{commentId}")
    public Comment getCommentById(@PathVariable long postId, @PathVariable long commentId) {
        return commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException("Comments for post " + postId + "not found"));
    }

//    POST /posts/{postId}/comments - создание нового комментария для поста.
//    Должны выводится только комментарий, принадлежащие посту.
    @PostMapping(path = "/{postId}/comments")
    public Comment createComment(@PathVariable long postId, @RequestBody Comment comment) {
        comment.setPost(postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post" + postId + "not found")));
        return commentRepository.save(comment);
    }

//    PATCH /posts/{postId}/comments/{commentId} - редактирование конкретного комментария поста.
//    Если такой комментарий не существует, должен вернуться ответ с кодом 404.
    @PatchMapping(path = "/{postId}/comments/{commentId}")
    public Comment updateComment(@PathVariable long postId, @PathVariable long commentId, @RequestBody Comment comment) {

        if (!commentRepository.existsById(commentId)) {
            throw new ResourceNotFoundException("Comment not found");
        }

        comment.setPost(postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post" + postId + "not found")));
        comment.setId(commentId);
        return commentRepository.save(comment);
    }

//    DELETE /posts/{postId}/comments/{commentId} - удаление конкретного комментария у поста.
//    Если такой комментарий не существует, должен вернуться ответ с кодом 404.
    @DeleteMapping(path = "/{postId}/comments/{commentId}")
    public void deleteComment(@PathVariable long postId, @PathVariable long commentId) {
        Comment comment = commentRepository.findByIdAndPostId(postId, commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        commentRepository.delete(comment);
    }


    // END
}
