package stackoverflow.answer.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Builder
@Getter
public class AnswerResponseDto {
    private long answerId;
    private long vote;
    private String content;
    private LocalDateTime modifiedTime = LocalDateTime.now();
    private LocalDateTime createTime = LocalDateTime.now();
}
