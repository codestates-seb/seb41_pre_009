package stackoverflow.comment.mapper;

import org.mapstruct.Mapper;
import stackoverflow.comment.dto.CommentPatchDto;
import stackoverflow.comment.dto.CommentPostDto;
import stackoverflow.comment.dto.CommentResponseDto;
import stackoverflow.comment.entity.Comment;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment commentPostDtoToComment(CommentPostDto commentPostDto);

    Comment commentPatchDtoToComment(CommentPatchDto commentPatchDto);

    CommentResponseDto commentToCommentResponseDto(Comment comment);

    List<CommentResponseDto> commentsToCommentResponseDtos(List<Comment> comment);
}
