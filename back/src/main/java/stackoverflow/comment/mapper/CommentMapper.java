package stackoverflow.comment.mapper;

import org.mapstruct.Mapper;
import stackoverflow.answer.entity.Answer;
import stackoverflow.comment.dto.CommentDto;
import stackoverflow.comment.entity.Comment;
import stackoverflow.member.entity.Member;

import java.util.List;

import java.util.List;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    default Comment commentPostDtoToComment(CommentDto.Post requestBody){
        Comment comment = new Comment();
        Member member = new Member();
        member.setMemberId(requestBody.getMemberId());

        comment.setMember(member);
        comment.setText(requestBody.getText());
        return comment;
    };
    
    Comment commentPatchDtoToComment(CommentDto.Patch requestBody);
    Object commentToCommentResponseDto(Comment comment);

    List<CommentDto.Response> commentsToCommentResponseDtos(List<Comment> comments);

}
