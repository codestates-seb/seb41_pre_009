package stackoverflow.comment.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stackoverflow.comment.entity.Comment;
import stackoverflow.comment.repository.CommentRepository;
import stackoverflow.exception.BusinessLogicException;
import stackoverflow.exception.ExceptionCode;
import stackoverflow.member.service.MemberService;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class CommentService {
    private final MemberService memberService;
    private final CommentRepository commentRepository;

    public CommentService(MemberService memberService, CommentRepository commentRepository) {
        this.memberService = memberService;
        this.commentRepository = commentRepository;
    }

    public Comment createComment(Comment comment) {
        //회원이 존재하는지 확인
        memberService.findVerifiedMember(comment.getMember().getMemberId());

        return commentRepository.save(comment);
    }

    public Comment updateComment(Comment comment) {
        // 조회하려는 답변이 검증된 답변인지 확인(존재하는 답변인지 확인 등)
        Comment findComment = findVerifiedComment(comment.getCommentId());

       /* Optional.ofNullable(comment.getMember())
                .ifPresent(member -> findcomment.setMember(member));
        Optional.ofNullable(comment.getQuestion())
                .ifPresent(questiond -> findcomment.setQuestion(questiond));*/
        Optional.ofNullable(comment.getText())
                .ifPresent(text -> findComment.setText(text));

        // 수정된 시간 적용
       // findComment.setModifiedTime(LocalDateTime.now());

        return commentRepository.save(findComment);
    }

    public Comment findComment(long commentId) {
        return findVerifiedCommentByQuery(commentId);
    }

   /* public Page<comment> findcomments(int page, int size) {
        return commentRepository.findAll(PageRequest.of(page, size,
                Sort.by("commentId").descending()));
    }*/

    public void deleteComment(long commentId) {
        Comment comment = findVerifiedComment(commentId);
        commentRepository.delete(comment);
    }

    public Comment findVerifiedComment(long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        Comment findComment =
                optionalComment.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));

        return findComment;
    }

    private Comment findVerifiedCommentByQuery(long commentId) {
        Optional<Comment> optionalcomment = commentRepository.findByComment(commentId);
        Comment findComment =
                optionalcomment.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));

        return findComment;
    }
}
