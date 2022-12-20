package stackoverflow.comment.dto;

import lombok.Getter;
import stackoverflow.validator.NotSpace;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
public class CommentPostDto {
    @NotSpace(message = "내용을 채워주세요.")
    private String text;

    @Column(nullable = false)
    private LocalDateTime createTime = LocalDateTime.now();
}
