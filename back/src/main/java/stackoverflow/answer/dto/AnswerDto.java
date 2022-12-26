package stackoverflow.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import stackoverflow.member.entity.Member;
import stackoverflow.validator.NotSpace;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


public class AnswerDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        @Positive
        private long memberId;
        @Positive
        private long questionId;
        @NotSpace(message = "내용은 공백이 아니어야 합니다.")
        @Size(min = 30, message = "내용은 최소 30글자 이상 작성해야 합니다.")
        private String content;


    @Getter
    @AllArgsConstructor
    public static class Patch {
        private long answerId;
        @NotSpace(message = "내용은 공백이 아니어야 합니다.")
        @Size(min = 30, message = "내용은 최소 30글자 이상 작성해야 합니다.")
        private String content;

//        private Answer.AnswerStatus answerStatus;

        public void setAnswerId(long answerId) {
            this.answerId = answerId;
        }
    }

    @AllArgsConstructor
    @Getter
    public static class Response {
        private long answerId;
        private long memberId;

        private long questionId;
        private String content;

    }
}
