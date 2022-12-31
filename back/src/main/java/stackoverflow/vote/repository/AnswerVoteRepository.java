package stackoverflow.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stackoverflow.answer.entity.Answer;
import stackoverflow.member.entity.Member;
import stackoverflow.vote.entity.AnswerVote;

import java.util.List;

public interface AnswerVoteRepository extends JpaRepository<AnswerVote, Long> {
    List<AnswerVote> findAllByVoterAndAnswer(Member member, Answer answer);
    List<AnswerVote> findAllByAnswer(Answer answer);
}
