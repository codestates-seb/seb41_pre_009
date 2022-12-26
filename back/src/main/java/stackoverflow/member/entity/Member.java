package stackoverflow.member.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import stackoverflow.answer.entity.Answer;
import stackoverflow.audit.Auditable;
import stackoverflow.comment.entity.Comment;
import stackoverflow.question.entity.Question;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Member extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;


    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private MemberStatus memberStatus = MemberStatus.MEMBER_ACTIVE;


    @OneToMany(mappedBy = "member")
    List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    List<Comment> comments = new ArrayList<>();

    public Member(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public void setAnswer(Answer answer) {
        answers.add(answer);
        if (answer.getMember() != this) {
            answer.setMember(this);
        }
    }


    public enum MemberStatus {
        MEMBER_ACTIVE("활동중"),
        MEMBER_SLEEP("휴면 상태"),
        MEMBER_QUIT("탈퇴 상태");

        @Getter
        private String status;
        MemberStatus(String status) {
            this.status = status;
        }
    }
}
