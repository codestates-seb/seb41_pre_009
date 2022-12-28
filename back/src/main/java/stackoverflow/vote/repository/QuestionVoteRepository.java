package stackoverflow.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stackoverflow.member.entity.Member;
import stackoverflow.question.entity.Question;
import stackoverflow.vote.entity.QuestionVote;
import stackoverflow.vote.service.QuestionVoteService;

import java.util.List;

public interface QuestionVoteRepository extends JpaRepository<QuestionVote, Long> {
    //QuestionVote findByQuestionAndQuestionVoterId(Question question, long questionVoterId);
    List<QuestionVote> findAllByVoterAndQuestion(Member member, Question question);
    List<QuestionVote> findAllByQuestion(Question question);

    long countQuestionVoteByQuestion(Question question);

    long countQuestionVoteByQuestionAndQuestionVoterId(Question question, long questionVoterId);
}
