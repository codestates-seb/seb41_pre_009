package stackoverflow.vote.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import stackoverflow.vote.dto.VoteDto;
import stackoverflow.vote.service.QuestionVoteService;

@RestController
@RequestMapping("/questions")
public class QuestionVoteController {
    private final QuestionVoteService questionVoteService;

    public QuestionVoteController(QuestionVoteService questionVoteService) {
        this.questionVoteService = questionVoteService;
    }

    @PostMapping("/{question-id}/upvote")
    @ResponseStatus(HttpStatus.CREATED)
    public VoteDto.QuestionResponse postQuestionUpVote(@PathVariable("question-id") long questionId,
                                           @PathVariable("question-id") long questionVoterId) {
        return questionVoteService.saveQuestionVote(questionId, questionVoterId,1);
    }

    @PostMapping("/{question-id}/downvote")
    @ResponseStatus(HttpStatus.CREATED)
    public VoteDto.QuestionResponse postQuestionDownVote(@PathVariable("question-id") long questionId,
                                           @PathVariable("question-id") long questionVoterId) {
        return questionVoteService.saveQuestionVote(questionId, questionVoterId,-1);
    }
}
