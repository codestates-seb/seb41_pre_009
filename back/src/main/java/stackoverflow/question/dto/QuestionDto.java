package stackoverflow.question.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stackoverflow.answer.dto.AnswerDto;
import stackoverflow.answer.entity.Answer;
import stackoverflow.comment.dto.CommentDto;
import stackoverflow.member.dto.MemberDto;
import stackoverflow.member.entity.Member;
import stackoverflow.question.entity.Question;
import stackoverflow.validator.NotSpace;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

public class QuestionDto {
    //질문 생성 Dto
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post {
        private long questionWriterId;

        @NotBlank(message = "제목은 공백이 아니어야 합니다.")
        @Size(min = 15, max = 150, message = "제목은 최소 15 글자, 최대 150 글자 작성할 수 있습니다.")
        private String title;

        @NotBlank(message = "내용은 공백이 아니어야 합니다.")
        @Size(min = 30, message = "내용은 최소 30글자 이상 작성해야 합니다.")
        private String content;

    }

    //질문 수정 Dto
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Patch {
        private long questionId;

        @NotSpace(message = "제목은 공백이 아니어야 합니다.")
        @Size(min = 15, max = 150, message = "제목은 최소 15 글자, 최대 150 글자 작성할 수 있습니다.")
        private String title;

        @NotSpace(message = "내용은 공백이 아니어야 합니다.")
        @Size(min = 30, message = "내용은 최소 30글자 이상 작성해야 합니다.")
        private String content;

        private Question.QuestionStatus questionStatus;

        public void setQuestionId(long questionId) {
            this.questionId = questionId;
        }
    }

    //질문 상세 조회 Dto
    @AllArgsConstructor
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response {
        private long questionId;
        private long questionWriterId;
        private String title;
        private String content;
        private int view;
        private Question.QuestionStatus questionStatus;
        private List<AnswerDto.Response> answers;
    }

}
