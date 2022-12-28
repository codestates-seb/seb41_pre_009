package stackoverflow.vote.entity;

import lombok.*;
import org.hibernate.validator.constraints.Range;
import stackoverflow.audit.Auditable;
import stackoverflow.member.entity.Member;
import stackoverflow.question.entity.Question;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionVote extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionVoteId;

    @Column(name = "question_voter_id")
    private long questionVoterId;

    @ManyToOne
    @JoinColumn(name = "voter_id")
    private Member voter;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Column
    @Range(min = -1, max = 1)
    private int amount;


}
