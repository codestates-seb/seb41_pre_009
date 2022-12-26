package stackoverflow.comment.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import stackoverflow.answer.entity.Answer;
import stackoverflow.comment.dto.CommentDto;
import stackoverflow.comment.entity.Comment;
import stackoverflow.member.entity.Member;
import stackoverflow.question.entity.Question;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    default Comment commentPostDtoToComment(CommentDto.Post requestBody) {
        Comment comment = new Comment();
        Member member = new Member();
        Answer answer = new Answer();

        member.setMemberId(requestBody.getMemberId());
        answer.setAnswerId(requestBody.getAnswerId());

        comment.setMember(member);
        comment.setAnswer(answer);
        comment.setText(requestBody.getText());

        return comment;
    }
    Comment commentPatchDtoToComment(CommentDto.Patch requestBody);

    @Mapping(source = "answer.answerId", target = "answerId")
    @Mapping(source = "member.memberId", target = "memberId")
    CommentDto.Response commentToCommentResponseDto(Comment comment);

    List<CommentDto.Response> commentsToCommentResponseDtos(List<Comment> comments);

}
