package stackoverflow.answer.dto;

import lombok.Getter;
import stackoverflow.validator.NotSpace;

import java.time.LocalDateTime;

@Getter
public class AnswerPatchDto {
    private long answerId;

    // 글자수 제한
    @NotSpace(message = "내용을 채워주세요.")
    private String content;

    private long vote;

    //private LocalDateTime modifiedTime = LocalDateTime.now();

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }
}
