package stackoverflow.question.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import stackoverflow.question.entity.Question;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query(value = "SELECT c FROM Question c WHERE c.questionId = :questionId")
    Optional<Question> findByQuestion(long questionId);
}
