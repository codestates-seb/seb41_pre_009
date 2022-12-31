package stackoverflow.question.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import stackoverflow.member.service.MemberService;
import stackoverflow.question.dto.QuestionDto;
import stackoverflow.question.entity.Question;
import stackoverflow.question.mapper.QuestionMapper;
import stackoverflow.question.repository.QuestionRepository;
import stackoverflow.question.service.QuestionService;
import stackoverflow.response.MultiResponseDto;
import stackoverflow.response.MultiResponseDto2;
import stackoverflow.response.SingleResponseDto;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/questions")
@Validated
@Slf4j
@CrossOrigin
public class QuestionController {
    private final QuestionRepository questionRepository;
    private final static String QUESTION_DEFAULT_URL = "/questions";
    private final QuestionService questionService;
    private final QuestionMapper mapper;
    private final MemberService memberService;

    public QuestionController(QuestionService questionService, QuestionMapper mapper, MemberService memberService,
                              QuestionRepository questionRepository) {
        this.questionService = questionService;
        this.mapper = mapper;
        this.memberService = memberService;
        this.questionRepository = questionRepository;
    }


    @PostMapping
    public ResponseEntity postQuestion(@RequestBody QuestionDto.Post requestBody) {
        Question question = mapper.questionPostDtoToQuestion(requestBody);

        long questionWriterId = question.getQuestionWriterId();
        Question createdQuestion = questionService.createQuestion(question,questionWriterId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.questionToQuestionResponseDto(createdQuestion)),
                HttpStatus.CREATED);
    }

    @PatchMapping("/{question-id}")
    public ResponseEntity patchQuestion(@PathVariable("question-id") @Positive long questionId,
                                        @Valid @RequestBody QuestionDto.Patch requestBody) {
        requestBody.setQuestionId(questionId);

        Question question =
                questionService.updateQuestion(mapper.questionPatchDtoToQuestion(requestBody));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.questionToQuestionResponseDto(question)),
                HttpStatus.OK);
    }

    @GetMapping("/{question-id}")
    public ResponseEntity getQuestion(@PathVariable("question-id") @Positive long questionId) {
        Question question = questionService.findQuestion(questionId);
        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.questionToQuestionResponseDto(question))
                , HttpStatus.OK);
    }

    /*@GetMapping
    public ResponseEntity getQuestions(@Positive @RequestParam int page,
                                       @Positive @RequestParam int size) {
        Page<Question> pageQuestions = questionService.findQuestions(page - 1, size);
        List<Question> questions = pageQuestions.getContent();
        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.questionsToQuestionResponseDtos(questions),
                        pageQuestions),
                HttpStatus.OK);
    }*/

    @GetMapping
    public ResponseEntity getQuestions() {
        List<Question> questions = questionService.findQuestions();
        return new ResponseEntity<>(
                new MultiResponseDto2<>(mapper.questionsToQuestionResponseDtos(questions)),
                HttpStatus.OK);
    }

    @DeleteMapping("/{question-id}")
    public ResponseEntity deleteQuestion(
            @PathVariable("question-id") @Positive long questionId) {
        questionService.deleteQuestion(questionId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
