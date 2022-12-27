package stackoverflow.answer.controller;


import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import stackoverflow.answer.dto.AnswerDto;
import stackoverflow.answer.entity.Answer;
import stackoverflow.answer.mapper.AnswerMapper;
import stackoverflow.answer.service.AnswerService;
import stackoverflow.member.service.MemberService;

import stackoverflow.question.entity.Question;
import stackoverflow.question.service.QuestionService;
import stackoverflow.response.MultiResponseDto;
import stackoverflow.response.SingleResponseDto;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/answers")
@Validated
public class AnswerController {
    private final static String Answer_DEFAULT_URL = "/answers";
    private AnswerService answerService;
    private QuestionService questionService;
    private AnswerMapper mapper;

    public AnswerController(AnswerService answerService, QuestionService questionService, AnswerMapper mapper) {
        this.answerService = answerService;
        this.questionService = questionService;
        this.mapper = mapper;
    }

    @PostMapping("/reply/{question-id}")
    public ResponseEntity postAnswer(@Valid @RequestBody AnswerDto.Post requestBody,
                                     @PathVariable("question-id") @Positive long questionId) {
        Answer answer = mapper.answerPostDtoToAnswer(requestBody);

        long answerWriterId = answer.getAnswerWriterId();
        Question question = questionService.findQuestion(questionId);

        answer.setQuestion(question);

        Answer createdAnswer = answerService.createAnswer(answer,answerWriterId);

       return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.answerToAnswerResponseDto(createdAnswer))
                , HttpStatus.CREATED);
    }

    @PatchMapping("/{answer-id}")
    public ResponseEntity patchAnswer(@PathVariable("answer-id") @Positive long answerId,
                                      @Valid @RequestBody AnswerDto.Patch requestBody) {
        requestBody.setAnswerId(answerId);
        Answer answer = answerService.updateAnswer(mapper.answerPatchDtoToAnswer(requestBody));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.answerToAnswerResponseDto(answer)),
                HttpStatus.OK);
    }

    @GetMapping("/{answer-id}")
    public ResponseEntity getAnswer(@PathVariable("answer-id") long answerId) {
        Answer answer = answerService.findAnswer(answerId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.answerToAnswerResponseDto(answer)),
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAnswers(@Positive @RequestParam int page,
                                     @Positive @RequestParam int size) {
        Page<Answer> pageAnswers = answerService.findAnswers(page - 1, size);
        List<Answer> answers = pageAnswers.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.answersToAnswerResponseDtos(answers),
                        pageAnswers),
                HttpStatus.OK);
    }

    @DeleteMapping("/{answer-id}")
    public ResponseEntity deleteAnswer(@PathVariable("answer-id") long answerId) {
        answerService.deleteAnswer(answerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
