package stackoverflow.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import stackoverflow.validator.NotSpace;

public class CommentDto {

    @Getter
    public static class Post {
        @NotSpace(message = "내용을 채워주세요.")
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
        private long commentId;
        private String content;
    }
}