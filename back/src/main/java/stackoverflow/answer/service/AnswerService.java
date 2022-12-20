package stackoverflow.answer.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stackoverflow.answer.entity.Answer;
import stackoverflow.answer.repository.AnswerRepository;
import stackoverflow.exception.BusinessLogicException;
import stackoverflow.exception.ExceptionCode;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class AnswerService {
//    private final MemberService memberService;
    private final AnswerRepository answerRepository;

    /*public AnswerService(MemberService memberService, AnswerRepository answerRepository) {
        this.memberService = memberService;
        this.answerRepository = answerRepository;
    }*/

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public Answer createAnswer(Answer answer) {
        //findverifiedexistAnswer로직 찾아보기
        //회원이 존재하는지 확인
        //memberService.findVerifiedMember(answer.getMember().getMemberId());

        //추후에 알아보기
        // createAnswer.setCreateTime(LocalDateTime.now());

        return answerRepository.save(answer);
    }

    public Answer updateAnswer(Answer answer) {
        // 조회하려는 답변이 검증된 답변인지 확인(존재하는 답변인지 확인 등)
        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());

        Optional.ofNullable(answer.getContent())
                .ifPresent(content -> findAnswer.setContent(content));

        // 수정된 시간 적용
        findAnswer.setModifiedTime(LocalDateTime.now());

        return answerRepository.save(findAnswer);
    }

    public Answer findAnswer(long answerId) {
        return findVerifiedAnswerByQuery(answerId);
    }

    // 페이지네이션 FE분들과 상의 / 중요도 하

    /*public Page<Answer> findAnswers(int page, int size) {
        return answerRepository.findAll(PageRequest.of(page, size,
                Sort.by("AnswerId").descending()));
    }*/

    public void deleteAnswer(long answerId) {
        Answer answer = findVerifiedAnswer(answerId);
        answerRepository.delete(answer);
    }

    public Answer findVerifiedAnswer(long answerId) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
        Answer findAnswer =
                optionalAnswer.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));

        return findAnswer;
    }

    private Answer findVerifiedAnswerByQuery(long answerId) {
        Optional<Answer> optionalAnswer = answerRepository.findByAnswer(answerId);
        Answer findAnswer =
                optionalAnswer.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));

        return findAnswer;
    }
}
