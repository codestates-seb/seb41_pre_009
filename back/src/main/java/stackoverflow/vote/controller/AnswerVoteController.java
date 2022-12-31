package stackoverflow.vote.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import stackoverflow.vote.dto.VoteDto;
import stackoverflow.vote.service.AnswerVoteService;

@RestController
@RequestMapping("/answers")
public class AnswerVoteController {
    private final AnswerVoteService answerVoteService;

    public AnswerVoteController(AnswerVoteService answerVoteService) {
        this.answerVoteService = answerVoteService;
    }

    @PostMapping("/{answer-id}/upvote")
    @ResponseStatus(HttpStatus.CREATED)
    public VoteDto.AnswerResponse postAnswerUpVote(@PathVariable("answer-id") long answerId,
                                                       @PathVariable("answer-id") long answerVoterId) {
        return answerVoteService.saveAnswerVote(answerId, answerVoterId, 1);
    }

    @PostMapping("/{answer-id}/downvote")
    @ResponseStatus(HttpStatus.CREATED)
    public VoteDto.AnswerResponse postAnswerDownVote(@PathVariable("answer-id") long answerId,
                                                         @PathVariable("answer-id") long answerVoterId) {
        return answerVoteService.saveAnswerVote(answerId, answerVoterId, -1);
    }
}
