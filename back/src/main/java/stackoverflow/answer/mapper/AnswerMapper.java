package stackoverflow.answer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import stackoverflow.answer.dto.AnswerDto;
import stackoverflow.answer.entity.Answer;
import stackoverflow.member.entity.Member;

@Mapper(componentModel = "spring")
public interface AnswerMapper {

    // Value Object를 사용
    default Answer answerPostDtoToAnswer(AnswerDto.Post requestBody) {
        Answer answer = new Answer();
        Member member = new Member();
        member.setMemberId(requestBody.getMemberId());

        answer.setMember(member);
        answer.setContent(requestBody.getContent());
        return answer;
    }
    Answer answerPatchDtoToAnswer(AnswerDto.Patch requestBody);


    @Mapping(source = "member.memberId", target = "memberId")
    @Mapping(source = "question.questionId", target = "questionId")
    AnswerDto.Response answerToAnswerResponseDto(Answer answer);

//    List<AnswerResponseDto> answersToAnswerResponseDtos(List<Answer> answer);

}
