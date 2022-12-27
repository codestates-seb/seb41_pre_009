package stackoverflow.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import stackoverflow.comment.entity.Comment;
import stackoverflow.member.entity.Member;
import stackoverflow.validator.NotSpace;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;


public class AnswerDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        private long answerWriterId;

        @NotSpace(message = "내용을 채워주세요.")
        private String content;

    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private long answerId;

        @NotSpace(message = "내용을 채워주세요.")
        private String content;

        public void setAnswerId(long answerId) {
            this.answerId = answerId;
        }
    }

    @AllArgsConstructor
    @Getter
    public static class Response {
        private long answerId;
        private long answerWriterId;
        private String content;

        private List<Comment> comments;
    }
}
