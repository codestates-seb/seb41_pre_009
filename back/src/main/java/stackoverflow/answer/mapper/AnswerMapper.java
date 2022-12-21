package stackoverflow.answer.mapper;

import org.mapstruct.Mapper;
import stackoverflow.answer.dto.AnswerDto;
import stackoverflow.answer.entity.Answer;

@Mapper(componentModel = "spring")
public interface AnswerMapper {

    // Value Object를 사용
    Answer answerPostDtoToAnswer(AnswerDto.Post requestBody);

    Answer answerPatchDtoToAnswer(AnswerDto.Patch requestBody);

    AnswerDto answerToAnswerResponseDto(Answer answer);

//    List<AnswerResponseDto> answersToAnswerResponseDtos(List<Answer> answer);

}
