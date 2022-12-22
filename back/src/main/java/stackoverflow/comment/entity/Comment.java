package stackoverflow.comment.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stackoverflow.answer.entity.Answer;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Comment {
//public class comment extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(length = 100, nullable = false)
    private String text;

/*
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
*/

    @ManyToOne
    @JoinColumn(name = "ANSWER_ID")
    private Answer answer;

}
