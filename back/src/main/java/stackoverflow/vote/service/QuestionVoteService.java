package stackoverflow.vote.service;

import org.springframework.stereotype.Service;
import stackoverflow.member.entity.Member;
import stackoverflow.member.repository.MemberRepository;
import stackoverflow.member.service.MemberService;
import stackoverflow.question.entity.Question;
import stackoverflow.question.repository.QuestionRepository;
import stackoverflow.question.service.QuestionService;
import stackoverflow.vote.dto.VoteDto;
import stackoverflow.vote.entity.QuestionVote;
import stackoverflow.vote.repository.QuestionVoteRepository;

import java.util.List;

@Service
public class QuestionVoteService {
    private final QuestionVoteRepository questionVoteRepository;
    private final MemberService memberService;
    private final QuestionService questionService;
    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;

    public QuestionVoteService(QuestionVoteRepository questionVoteRepository,
                               MemberService memberService,
                               QuestionService questionService,
                               QuestionRepository questionRepository,
                               MemberRepository memberRepository) {
        this.questionVoteRepository = questionVoteRepository;
        this.memberService = memberService;
        this.questionService = questionService;
        this.questionRepository = questionRepository;
        this.memberRepository = memberRepository;
    }

    public VoteDto.QuestionResponse saveQuestionVote(Long questionId, Long questionVoterId, int amount) {
        Question findQuestion = questionService.findVerifiedQuestion(questionId);
        Member findMember = memberService.findVerifiedMember(questionVoterId);
        List<QuestionVote> votes = questionVoteRepository.findAllByVoterAndQuestion(findMember,findQuestion);

        if (votes == null) {
            QuestionVote createdVote = createVote(findQuestion, findMember, amount);
            questionVoteRepository.save(createdVote);

            if (amount == 1) {
                return VoteDto.QuestionResponse.builder()
                        .questionVoterId(questionVoterId)
                        .voteCount(questionVoteRepository
                                .findAllByQuestion(findQuestion)
                                .stream()
                                .mapToInt(QuestionVote::getAmount)
                                .sum() + 1)
                        .isUpVoter(amount == 1)
                        .isDownVoter(amount == -1)
                        .questionId(questionId)
                        .build();
            } else {
                return VoteDto.QuestionResponse.builder()
                        .questionVoterId(questionVoterId)
                        .voteCount(questionVoteRepository
                                .findAllByQuestion(findQuestion)
                                .stream()
                                .mapToInt(QuestionVote::getAmount)
                                .sum() - 1)
                        .isUpVoter(amount == 1)
                        .isDownVoter(amount == -1)
                        .questionId(questionId)
                        .build();
            }

        }
        else {
            questionVoteRepository.deleteAll(votes);
            if (amount == 1) {
                return VoteDto.QuestionResponse.builder()
                        .questionVoterId(questionVoterId)
                        .voteCount(questionVoteRepository
                                .findAllByQuestion(findQuestion)
                                .stream()
                                .mapToInt(QuestionVote::getAmount)
                                .sum() + 1)
                        .isUpVoter(true)
                        .isDownVoter(false)
                        .questionId(questionId)
                        .build();
            } else {
                return VoteDto.QuestionResponse.builder()
                        .questionVoterId(questionVoterId)
                        .voteCount(questionVoteRepository
                                .findAllByQuestion(findQuestion)
                                .stream()
                                .mapToInt(QuestionVote::getAmount)
                                .sum() - 1)
                        .isUpVoter(false)
                        .isDownVoter(true)
                        .questionId(questionId)
                        .build();
            }
        }

    }
    private QuestionVote createVote(Question question, Member member, int amount) {
        return QuestionVote.builder()
                .voter(member)
                .question(question)
                .amount(amount)
                .build();
    }
}
