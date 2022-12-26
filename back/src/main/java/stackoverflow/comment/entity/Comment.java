package stackoverflow.comment.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stackoverflow.answer.entity.Answer;
import stackoverflow.audit.Auditable;
import stackoverflow.member.entity.Member;


import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Comment extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "ANSWER_ID")
    private Answer answer;

    public void setMember(Member member) {
        this.member = member;
        if (!this.member.getComments().contains(this)) {
            this.member.getComments().add(this);
        }
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
        if (!this.answer.getComments().contains(this)) {
            this.answer.getComments().add(this);
        }
    }

    public Comment(String text) {
        this.text = text;
    }
}
