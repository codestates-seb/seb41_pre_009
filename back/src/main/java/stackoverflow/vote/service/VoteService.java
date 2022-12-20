package stackoverflow.vote.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stackoverflow.exception.BusinessLogicException;
import stackoverflow.exception.ExceptionCode;
import stackoverflow.vote.entity.Vote;
import stackoverflow.vote.repository.VoteRepository;

import java.util.Optional;

@Service
@Transactional
public class VoteService {
//    private final MemberService memberService;
    private final VoteRepository voteRepository;

/*
    public VoteService(MemberService memberService, VoteRepository voteRepository) {
        this.memberService = memberService;
        this.voteRepository = voteRepository;
    }
*/

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public Vote createVote(Vote vote) {
        //회원이 존재하는지 확인
//        memberService.findVerifiedMember(vote.getMember().getMemberId());

        return voteRepository.save(vote);
    }

    public void deleteVote(long voteId) {
        Vote vote = findVerifiedVote(voteId);
        voteRepository.delete(vote);
    }

    public Vote findVerifiedVote(long voteId) {
        Optional<Vote> optionalVote = voteRepository.findById(voteId);
        Vote findVote =
                optionalVote.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.VOTE_NOT_FOUND));

        return findVote;
    }

    private Vote findVerifiedVoteByQuery(long voteId) {
        Optional<Vote> optionalVote = voteRepository.findByVote(voteId);
        Vote findVote =
                optionalVote.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.VOTE_NOT_FOUND));

        return findVote;
    }
}
