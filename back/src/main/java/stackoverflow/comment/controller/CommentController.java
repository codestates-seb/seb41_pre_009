package stackoverflow.comment.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import stackoverflow.comment.dto.CommentPatchDto;
import stackoverflow.comment.dto.CommentPostDto;
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
    private final static String comment_DEFAULT_URL = "/comments";
    private CommentService commentService;
    private CommentMapper mapper;

    public CommentController(CommentService commentService, CommentMapper commentMapper) {
        this.commentService = commentService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postcomment(@Valid @RequestBody CommentPostDto commentPostDto) {
        Comment comment = commentService.createComment(mapper.commentPostDtoToComment(commentPostDto));
        URI location = UriCreator.createUri(comment_DEFAULT_URL, comment.getCommentId());

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{comment-id}")
    public ResponseEntity patchComment(@PathVariable("comment-id") @Positive long commentId,
                                      @Valid @RequestBody CommentPatchDto commentPatchDto) {
        commentPatchDto.setcommentId(commentId);
        Comment comment = commentService.updateComment(mapper.commentPatchDtoToComment(commentPatchDto));

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