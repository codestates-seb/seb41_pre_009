package stackoverflow.vote.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import stackoverflow.vote.dto.VoteDto;
import stackoverflow.vote.service.QuestionVoteService;

@RestController
@RequestMapping("/questionvote")
public class QuestionVoteController {
    private final QuestionVoteService questionVoteService;

    public QuestionVoteController(QuestionVoteService questionVoteService) {
        this.questionVoteService = questionVoteService;
    }

    @PostMapping("/{question-id}/{question-voter-id}/upvotes")
    @ResponseStatus(HttpStatus.CREATED)
    public VoteDto.QuestionResponse postQuestionUpVote(@PathVariable("question-id") long questionId,
                                           @PathVariable("question-voter-id") long questionVoterId) {
        return questionVoteService.saveQuestionVote(questionId, questionVoterId,1);
    }


    @PostMapping("/{question-id}/{question-voter-id}/downvotes")
    @ResponseStatus(HttpStatus.CREATED)
    public VoteDto.QuestionResponse postQuestionDownVote(@PathVariable("question-id") long questionId,
                                           @PathVariable("question-voter-id") long questionVoterId) {
        return questionVoteService.saveQuestionVote(questionId, questionVoterId,-1);
    }
}
