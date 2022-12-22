package stackoverflow.comment.mapper;

import org.mapstruct.Mapper;
import stackoverflow.comment.dto.CommentDto;
import stackoverflow.comment.entity.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    // Value Object를 사용


    Comment commentPostDtoToComment(CommentDto.Post requestBody);
    Comment commentPatchDtoToComment(CommentDto.Patch requestBody);
    Object commentToCommentResponseDto(Comment comment);



//    List<commentResponseDto> commentsTocommentResponseDtos(List<comment> comment);

}
