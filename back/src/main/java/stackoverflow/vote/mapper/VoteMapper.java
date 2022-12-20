package stackoverflow.vote.mapper;

import org.mapstruct.Mapper;
import stackoverflow.vote.dto.VotePostDto;
import stackoverflow.vote.entity.Vote;

@Mapper(componentModel = "spring")
public interface VoteMapper {

    // Value Object를 사용
    Vote votePostDtoToVote(VotePostDto votePostDto);
}
