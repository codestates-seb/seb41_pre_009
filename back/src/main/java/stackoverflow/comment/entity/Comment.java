package stackoverflow.comment.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Auditable;
import stackoverflow.answer.entity.Answer;
import stackoverflow.validator.NotSpace;

import javax.persistence.*;
import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class Comment {
//public class Comment extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @NotSpace(message = "내용을 채워주세요.")
    private String text;

    /*@ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;
*/
    @ManyToOne
    @JoinColumn(name = "ANSWER_ID")
    private Answer answer;

    @Column(nullable = false)
    private LocalDateTime createTime = LocalDateTime.now();

    @Column(nullable = false, name = "LAST_MODIFIED_TIME")
    private LocalDateTime modifiedTime = LocalDateTime.now();
}
