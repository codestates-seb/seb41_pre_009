package stackoverflow.answer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import stackoverflow.answer.dto.AnswerDto;
import stackoverflow.answer.entity.Answer;
import stackoverflow.member.entity.Member;
import stackoverflow.question.entity.Question;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    Answer answerPostDtoToAnswer(AnswerDto.Post requestBody);

    Answer answerPatchDtoToAnswer(AnswerDto.Patch requestBody);

    AnswerDto.Response answerToAnswerResponseDto(Answer answer);

    List<AnswerDto.Response> answersToAnswerResponseDtos(List<Answer> answer);

}
