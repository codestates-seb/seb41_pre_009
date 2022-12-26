package stackoverflow.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import stackoverflow.member.entity.Member;
import stackoverflow.validator.NotSpace;


public class AnswerDto {
    @Getter
    public static class Post {
        @NotSpace(message = "내용을 채워주세요.")
        private String content;
        private long answerVoteCount;


        public Post(String content, long answerVoteCount) {
            this.content = content;
            this.answerVoteCount = answerVoteCount;
        }
  }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private long answerId;

        @NotSpace(message = "내용을 채워주세요.")
        private String content;
        private long answerVoteCount;

        public void setAnswerId(long answerId) {
            this.answerId = answerId;
        }
    }

    @AllArgsConstructor
    @Getter
    public static class Response {
        private long answerId;
        private String content;
        private long answerVoteCount;


    }
}
