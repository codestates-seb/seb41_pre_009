package stackoverflow.vote.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class VoteResponseDto {
    private Long voteId;
    private String count;
}
