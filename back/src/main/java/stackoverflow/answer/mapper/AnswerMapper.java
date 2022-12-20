package stackoverflow.answer.mapper;

import org.mapstruct.Mapper;
import stackoverflow.answer.dto.AnswerPatchDto;
import stackoverflow.answer.dto.AnswerPostDto;
import stackoverflow.answer.dto.AnswerResponseDto;
import stackoverflow.answer.entity.Answer;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerMapper {

    // Value Object를 사용
    Answer answerPostDtoToAnswer(AnswerPostDto answerPostDto);

    Answer answerPatchDtoToAnswer(AnswerPatchDto answerPatchDto);

    AnswerResponseDto answerToAnswerResponseDto(Answer answer);

//    List<AnswerResponseDto> answersToAnswerResponseDtos(List<Answer> answer);

}
