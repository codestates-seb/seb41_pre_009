package stackoverflow.comment.dto;

import lombok.Getter;
import stackoverflow.validator.NotSpace;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
public class CommentPatchDto {
    private long commentId;

    @NotSpace(message = "내용을 채워주세요.")
    private String text;

    @Column(nullable = false, name = "LAST_MODIFIED_TIME")
    private LocalDateTime modifiedTime = LocalDateTime.now();

    public void setcommentId(long commentId) {
        this.commentId = commentId;
    }
}
