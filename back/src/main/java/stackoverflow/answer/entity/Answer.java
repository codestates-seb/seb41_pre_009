package stackoverflow.answer.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Auditable;

import javax.persistence.*;
import java.lang.reflect.Member;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Answer {
//public class Answer extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Column(length = 100, nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    /*@ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;*/

    @Column(nullable = false)
    private LocalDateTime createTime = LocalDateTime.now();

    @Column(nullable = false, name = "LAST_MODIFIED_TIME")
    private LocalDateTime modifiedTime = LocalDateTime.now();

}
