package stackoverflow.comment.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stackoverflow.answer.entity.Answer;
import stackoverflow.audit.Auditable;
import stackoverflow.member.entity.Member;


import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Comment extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(length = 500, nullable = false)
    private String text;

    /*Member name
    @Column
    private String name;*/

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "ANSWER_ID")
    private Answer answer;

    //댓글 내용(comment)을 입력받아 돌려주는 생성자
    public Comment(String text) {
        this.text = text;
    }
}
