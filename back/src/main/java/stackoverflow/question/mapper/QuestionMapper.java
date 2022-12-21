package stackoverflow.question.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import stackoverflow.question.dto.QuestionDto;
import stackoverflow.question.entity.Question;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    //v1.0
    Question questionPostDtoToQuestion(QuestionDto.Post requestBody);

    Question questionPatchDtoToQuestion(QuestionDto.Patch requestBody);

    //@Mapping(source = "question.vote.voteCount", target = "voteCount")
    QuestionDto.Response questionToQuestionResponseDto(Question question);

    List<QuestionDto.Response> questionsToQuestionResponseDtos(List<Question> questions);
}
