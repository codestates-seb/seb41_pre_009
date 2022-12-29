package stackoverflow.vote.service;

import org.springframework.stereotype.Service;
import stackoverflow.answer.entity.Answer;
import stackoverflow.answer.service.AnswerService;
import stackoverflow.member.entity.Member;
import stackoverflow.member.service.MemberService;
import stackoverflow.vote.dto.VoteDto;
import stackoverflow.vote.entity.AnswerVote;

import stackoverflow.vote.repository.AnswerVoteRepository;

import java.util.List;

@Service
public class AnswerVoteService {
    private final AnswerVoteRepository answerVoteRepository;
    private final MemberService memberService;
    private final AnswerService answerService;

    public AnswerVoteService(AnswerVoteRepository answerVoteRepository,
                             MemberService memberService,
                             AnswerService answerService) {
        this.answerVoteRepository = answerVoteRepository;
        this.memberService = memberService;
        this.answerService = answerService;
    }

    public VoteDto.AnswerResponse saveAnswerVote(Long answerId, Long answerVoterId, int amount) {
        Answer findAnswer = answerService.findVerifiedAnswer(answerId);
        Member findMember = memberService.findVerifiedMember(answerVoterId);
        List<AnswerVote> votes = answerVoteRepository.findAllByVoterAndAnswer(findMember,findAnswer);

        if (votes == null) {
            AnswerVote createdVote = createVote(findAnswer, findMember, amount);
            answerVoteRepository.save(createdVote);

            if (amount == 1) {
                return VoteDto.AnswerResponse.builder()
                        .answerVoterId(answerVoterId)
                        .voteCount(answerVoteRepository
                                .findAllByAnswer(findAnswer)
                                .stream()
                                .mapToInt(AnswerVote::getAmount)
                                .sum() + 1)
                        .isUpVoter(amount == 1)
                        .isDownVoter(amount == -1)
                        .answerId(answerId)
                        .build();
            } else {
                return VoteDto.AnswerResponse.builder()
                        .answerVoterId(answerVoterId)
                        .voteCount(answerVoteRepository
                                .findAllByAnswer(findAnswer)
                                .stream()
                                .mapToInt(AnswerVote::getAmount)
                                .sum() - 1)
                        .isUpVoter(amount == 1)
                        .isDownVoter(amount == -1)
                        .answerId(answerId)
                        .build();
            }

        }
        else {
            answerVoteRepository.deleteAll(votes);
            if (amount == 1) {
                return VoteDto.AnswerResponse.builder()
                        .answerVoterId(answerVoterId)
                        .voteCount(answerVoteRepository
                                .findAllByAnswer(findAnswer)
                                .stream()
                                .mapToInt(AnswerVote::getAmount)
                                .sum() + 1)
                        .isUpVoter(true)
                        .isDownVoter(false)
                        .answerId(answerId)
                        .build();
            } else {
                return VoteDto.AnswerResponse.builder()
                        .answerVoterId(answerVoterId)
                        .voteCount(answerVoteRepository
                                .findAllByAnswer(findAnswer)
                                .stream()
                                .mapToInt(AnswerVote::getAmount)
                                .sum() - 1)
                        .isUpVoter(false)
                        .isDownVoter(true)
                        .answerId(answerId)
                        .build();
            }
        }

    }
    private AnswerVote createVote(Answer answer, Member member, int amount) {
        return AnswerVote.builder()
                .voter(member)
                .answer(answer)
                .amount(amount)
                .build();
    }
}
