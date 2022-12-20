package stackoverflow.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import stackoverflow.vote.entity.Vote;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    @Query(value = "SELECT c FROM Vote c WHERE c.voteId = :voteId")
    Optional<Vote> findByVote(long voteId);
}
