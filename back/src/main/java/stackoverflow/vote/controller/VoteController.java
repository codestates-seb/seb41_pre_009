package stackoverflow.vote.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import stackoverflow.utils.UriCreator;
import stackoverflow.vote.dto.VotePostDto;
import stackoverflow.vote.entity.Vote;
import stackoverflow.vote.mapper.VoteMapper;
import stackoverflow.vote.service.VoteService;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/votes")
@Validated
public class VoteController {
    private final static String VOTE_DEFAULT_URL = "/votes";
    private VoteService voteService;
    private VoteMapper mapper;

    public VoteController(VoteService voteService, VoteMapper mapper) {
        this.voteService = voteService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postVote(@Valid @RequestBody VotePostDto votePostDto) {
        Vote vote = voteService.createVote(mapper.votePostDtoToVote(votePostDto));
        URI location = UriCreator.createUri(VOTE_DEFAULT_URL, vote.getVoteId());

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{vote-id}")
    public ResponseEntity deleteVote(@PathVariable("vote-id") long voteId) {
        voteService.deleteVote(voteId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
