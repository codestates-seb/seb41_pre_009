package stackoverflow.question.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stackoverflow.question.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    //v1.0
}
