package stackoverflow.comment.mapper;

import org.mapstruct.Mapper;
import stackoverflow.answer.entity.Answer;
import stackoverflow.comment.dto.CommentDto;
import stackoverflow.comment.entity.Comment;
import stackoverflow.question.entity.Question;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    default Comment commentPostDtoToComment(CommentDto.Post requestBody) {
        Comment comment = new Comment();
        Answer answer = new Answer();

        answer.setAnswerId(answer.getAnswerId());
        comment.setAnswer(answer);

        return comment;
    }
    Comment commentPatchDtoToComment(CommentDto.Patch requestBody);
    Object commentToCommentResponseDto(Comment comment);

    List<CommentDto.Response> commentsToCommentResponseDtos(List<Comment> comments);

}
