package stackoverflow.comment.controller;


import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import stackoverflow.answer.entity.Answer;
import stackoverflow.comment.dto.CommentDto;
import stackoverflow.comment.entity.Comment;
import stackoverflow.comment.mapper.CommentMapper;
import stackoverflow.comment.service.CommentService;
import stackoverflow.response.MultiResponseDto;
import stackoverflow.response.SingleResponseDto;
import stackoverflow.utils.UriCreator;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/comments")
@Validated
public class CommentController {
    private final static String COMMENT_DEFAULT_URL = "/comments";
    private CommentService commentService;
    private CommentMapper mapper;

    public CommentController(CommentService commentService, CommentMapper mapper) {
        this.commentService = commentService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postComment(@Valid @RequestBody CommentDto.Post requestBody) {
        Comment comment = commentService.createComment(mapper.commentPostDtoToComment(requestBody));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.commentToCommentResponseDto(comment))
                , HttpStatus.CREATED);
    }

    @PatchMapping("/{comment-id}")
    public ResponseEntity patchComment(@PathVariable("comment-id") @Positive long commentId,
                                       @Valid @RequestBody CommentDto.Patch requestBody) {
        requestBody.setCommentId(commentId);

        Comment comment = commentService.updateComment(mapper.commentPatchDtoToComment(requestBody));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.commentToCommentResponseDto(comment)),
                HttpStatus.OK);
    }

    @GetMapping("/{comment-id}")
    public ResponseEntity getComment(@PathVariable("comment-id") long commentId) {
        Comment comment = commentService.findComment(commentId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.commentToCommentResponseDto(comment)),
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getComments(@Positive @RequestParam int page,
                                      @Positive @RequestParam int size) {
        Page<Comment> pageComments = commentService.findComments(page - 1, size);
        List<Comment> comments = pageComments.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.commentsToCommentResponseDtos(comments),
                        pageComments),
                HttpStatus.OK);
    }

    @DeleteMapping("/{comment-id}")
    public ResponseEntity deleteComment(@PathVariable("comment-id") long commentId) {
        commentService.deleteComment(commentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
