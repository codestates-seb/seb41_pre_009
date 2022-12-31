package stackoverflow.question.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import stackoverflow.member.entity.Member;
import stackoverflow.question.dto.QuestionDto;
import stackoverflow.question.entity.Question;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
/*    default
    Question questionPostDtoToQuestion(QuestionDto.Post requestBody)
    {
        Question question = new Question();
        Member member = new Member();

        question.setMember(member);
        question.setTitle(requestBody.getTitle());
        question.setContent(requestBody.getContent());
        return question;
    }*/

    Question questionPostDtoToQuestion(QuestionDto.Post requestBody);

    Question questionPatchDtoToQuestion(QuestionDto.Patch requestBody);

    //@Mapping(source = "member.memberId", target = "memberId")
    QuestionDto.Response questionToQuestionResponseDto(Question question);

    List<QuestionDto.Response> questionsToQuestionResponseDtos(List<Question> questions);
}
