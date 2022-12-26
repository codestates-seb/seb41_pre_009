package stackoverflow.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import stackoverflow.validator.NotSpace;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class CommentDto {

    @Getter
    @AllArgsConstructor
    public static class Post {
        @Positive
        private long memberId;

        @Positive
        private long answerId;

        @NotBlank(message = "내용을 채워주세요.")
        private String text;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private long commentId;
        @NotSpace(message = "내용을 채워주세요.")
        private String text;
        public void setCommentId(long commentId) {
            this.commentId = commentId;
        }
    }

    @AllArgsConstructor
    @Getter
    public static class Response {
        private long memberId;
        private long answerId;
        private long commentId;
        private String text;
    }
}