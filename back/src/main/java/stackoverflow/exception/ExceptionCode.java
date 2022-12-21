package stackoverflow.exception;

import lombok.Getter;

public enum ExceptionCode {
    ANSWER_NOT_FOUND(404, "Answer not found"),
    VOTE_NOT_FOUND(404, "Vote not found"),
    MEMBER_NOT_FOUND(404, "Member not found"),
    QUESTION_NOT_FOUND(404, "Question not found"),
    ANSWER_EXISTS(409, "Answer exists"),
    MEMBER_EXISTS(409, "Member exists"),
    QUESTION_EXISTS(409, "Question exists"),
    CANNOT_CHANGE_ANSWER(403, "Answer can not change"),
    CANNOT_CHANGE_QUESTION(403, "Question can not change"),
    CANNOT_CHANGE_COMMENT(403,"Comment can not change"),
    CANNOT_CHANGE_MEMBER(403, "Member can not change"),
    NOT_IMPLEMENTATION(501, "Not Implementation"),
    INVALID_MEMBER_STATUS(400, "Invalid member status");  // TO 추가된 부분

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
