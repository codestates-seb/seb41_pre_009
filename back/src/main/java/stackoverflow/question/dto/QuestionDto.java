package stackoverflow.question.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import stackoverflow.validator.NotSpace;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class QuestionDto {
    @Getter
    public static class Post {
        @NotBlank(message = "제목은 공백이 아니어야 합니다.")
        @Size(min = 15, max = 150, message = "제목은 최소 15 글자, 최대 150 글자 작성할 수 있습니다.")
        private String title;

        @NotBlank(message = "내용은 공백이 아니어야 합니다.")
        @Size(min = 30, message = "내용은 최소 30글자 이상 작성해야 합니다.")
        private String content;

    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private long questionId;

        @NotSpace(message = "제목은 공백이 아니어야 합니다.")
        @Size(min = 15, max = 150, message = "제목은 최소 15 글자, 최대 150 글자 작성할 수 있습니다.")
        private String title;

        @NotSpace(message = "내용은 공백이 아니어야 합니다.")
        @Size(min = 30, message = "내용은 최소 30글자 이상 작성해야 합니다.")
        private String content;

        public void setQuestionId(long questionId) {
            this.questionId = questionId;
        }
    }

    @AllArgsConstructor
    @Getter
    public static class Response {
        private long questionId;
        private String title;
        private String content;
        private int view;
        //private int questionVoteCount;

    }

}
