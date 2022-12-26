package stackoverflow.question.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import stackoverflow.answer.entity.Answer;
import stackoverflow.audit.Auditable;
import stackoverflow.member.entity.Member;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class Question extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(nullable = false)
    @Size(min = 15, max = 150)
    private String title;

    @Column(nullable = false)
    @Size(min = 30)
    private String content;

    @Column
    private int view = 0;

    /*@Column
    private int vote = 0;*/

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private Question.QuestionStatus questionStatus = QuestionStatus.QUESTION_ACCEPTED;

    @OneToMany(mappedBy = "question")
    List<Answer> answers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;


    public void setMember(Member member) {
        this.member = member;
        if (!this.member.getQuestions().contains(this)) {
            this.member.getQuestions().add(this);
        }
    }

    public Question(String title, String content, Integer view) {
        this.title = title;
        this.content = content;
        this.view = view;
    }

    public enum QuestionStatus {
        QUESTION_ACCEPTED("등록된 질문"),
        QUESTION_DELETE("삭제된 질문");

        @Getter
        private String status;
        QuestionStatus(String status) {
            this.status = status;
        }
    }
}
