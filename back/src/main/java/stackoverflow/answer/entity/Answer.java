package stackoverflow.answer.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stackoverflow.audit.Auditable;
import stackoverflow.comment.entity.Comment;

import stackoverflow.member.entity.Member;
import stackoverflow.question.entity.Question;
import stackoverflow.vote.entity.AnswerVote;
import stackoverflow.vote.entity.QuestionVote;


import javax.persistence.*;
import javax.validation.constraints.Size;
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

    @Column(nullable = false, name="answer_writer_id")
    private long answerWriterId;

    @Column
    private Long answerVoteCount;

    @Column(nullable = false)
    @Size(min = 30)
    private String content;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @OneToMany(mappedBy = "answer")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "answer") //cascade = {CascadeType.REMOVE}
    private List<AnswerVote> votes = new ArrayList<>();

    //Answer와 연관관계를 맺을 대상인 member 객체
    public void setMember(Member member) {
        this.member = member;
        if (!this.member.getAnswers().contains(this)) {
            this.member.getAnswers().add(this);
        }
    }

    //Answer와 연관관계를 맺을 대상인 question 객체
    public void setQuestion(Question question) {
        this.question = question;
        if (!this.question.getAnswers().contains(this)) {
            this.question.getAnswers().add(this);
        }
    }

    public Answer(String content) {
        this.content = content;
    }
}
