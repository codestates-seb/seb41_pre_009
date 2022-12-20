package stackoverflow.answer.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Range;
import stackoverflow.validator.NotSpace;

import java.time.LocalDateTime;

@Getter
public class AnswerPostDto {

    //글자수 제한 어노테이션 달기
    @NotSpace(message = "내용을 채워주세요.")
    private String content;

    private long vote;

 //   private LocalDateTime createTime = LocalDateTime.now();
}
