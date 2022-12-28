package stackoverflow.vote.entity;

import lombok.*;
import org.hibernate.validator.constraints.Range;
import stackoverflow.answer.entity.Answer;
import stackoverflow.member.entity.Member;
import stackoverflow.question.entity.Question;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long answerVoteId;

    @Column(name = "answer_voter_id")
    private long answerVoterId;

    @ManyToOne
    @JoinColumn(name = "voter_id")
    private Member voter;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

    @Column
    @Range(min = -1, max = 1)
    private int amount;
}
