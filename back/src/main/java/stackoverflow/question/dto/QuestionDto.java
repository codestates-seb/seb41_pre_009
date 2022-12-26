package stackoverflow.question.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import stackoverflow.answer.dto.AnswerDto;
import stackoverflow.answer.entity.Answer;
import stackoverflow.comment.dto.CommentDto;
import stackoverflow.member.entity.Member;

import stackoverflow.question.entity.Question;
import stackoverflow.validator.NotSpace;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public class QuestionDto {
    //질문 생성 Dto
    @Getter
    @AllArgsConstructor
    public static class Post {
        @Positive
        private long memberId;

        @NotBlank(message = "제목은 공백이 아니어야 합니다.")
        @Size(min = 15, max = 150, message = "제목은 최소 15 글자, 최대 150 글자 작성할 수 있습니다.")
        private String title;

        @NotBlank(message = "내용은 공백이 아니어야 합니다.")
        @Size(min = 30, message = "내용은 최소 30글자 이상 작성해야 합니다.")
        private String content;

    }

    //질문 수정 Dto
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

        private Question.QuestionStatus questionStatus;

        public void setQuestionId(long questionId) {
            this.questionId = questionId;
        }
    }

    //질문 상세 조회 Dto
    @AllArgsConstructor
    @Getter
    public static class Response {
        private long questionId; //질문 Id
        private long memberId; //질문 생성한 유저 고유 Id
        private String title; //질문 제목
        private String content; //질문 내용
        private int view; //질문 조회 수
        private Question.QuestionStatus questionStatus; //질문 상태

        //private int vote;
        private List<Answer> answers; //질문에 달린 답변 정보

    }

}
