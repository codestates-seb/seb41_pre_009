package stackoverflow.comment.dto;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class CommentResponseDto {
    private long commentId;
    private String korName;
    private String engName;
    private int price;

}
