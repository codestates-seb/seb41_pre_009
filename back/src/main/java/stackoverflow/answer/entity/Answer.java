package stackoverflow.answer.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stackoverflow.audit.Auditable;
import stackoverflow.comment.entity.Comment;
import stackoverflow.member.entity.Member;
import stackoverflow.question.entity.Question;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Answer extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Column
    private Long answerVoteCount;

    @Column(length = 100, nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;


    @OneToMany(mappedBy = "answer")
    private List<Comment> comments = new ArrayList<>();
}
