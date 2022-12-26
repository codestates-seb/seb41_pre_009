package stackoverflow.answer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import stackoverflow.answer.dto.AnswerDto;
import stackoverflow.answer.entity.Answer;
import stackoverflow.member.entity.Member;
import stackoverflow.question.entity.Question;


@Mapper(componentModel = "spring")
public interface AnswerMapper {
    default Answer answerPostDtoToAnswer(AnswerDto.Post requestBody) {
        Answer answer = new Answer();
        Member member = new Member();
        Question question = new Question();

        member.setMemberId(requestBody.getMemberId());
        question.setQuestionId(requestBody.getQuestionId());

        answer.setMember(member);
        answer.setQuestion(question);
        answer.setContent(requestBody.getContent());
        return answer;
    }


    Answer answerPatchDtoToAnswer(AnswerDto.Patch requestBody);


    @Mapping(source = "member.memberId", target = "memberId")
    @Mapping(source = "question.questionId", target = "questionId")
    AnswerDto.Response answerToAnswerResponseDto(Answer answer);

//    List<AnswerResponseDto> answersToAnswerResponseDtos(List<Answer> answer);

}
