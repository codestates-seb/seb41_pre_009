package stackoverflow.question.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import stackoverflow.exception.BusinessLogicException;
import stackoverflow.exception.ExceptionCode;
import stackoverflow.member.entity.Member;
import stackoverflow.member.repository.MemberRepository;
import stackoverflow.member.service.MemberService;
import stackoverflow.question.entity.Question;
import stackoverflow.question.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
//    private final MemberService memberService;
    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;

    public QuestionService(QuestionRepository questionRepository,
                           MemberRepository memberRepository) {
        this.questionRepository = questionRepository;
        this.memberRepository = memberRepository;
    }

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }
    public Question createQuestion(Question question, long questionWriterId) {
        Member member = memberRepository.findByMemberId(questionWriterId);
        question.setMember(member);

        return questionRepository.save(question);
    }


    public Question updateQuestion(Question question) {
        Question findQuestion = findVerifiedQuestion(question.getQuestionId());

        Optional.ofNullable(question.getTitle()).ifPresent(title -> findQuestion.setTitle(title));
        Optional.ofNullable(question.getContent()).ifPresent(content -> findQuestion.setContent(content));

        return questionRepository.save(findQuestion);
    }

    public Question findQuestion(long questionId) {
        Question findQuestion = findVerifiedQuestionByQuery(questionId);

        //조회할 때마다 조회 수 1 증가
        findQuestion.setView(findQuestion.getView() + 1);
        questionRepository.save(findQuestion);

        return findQuestion;
    }

    /*public Page<Question> findQuestions(int page, int size) {
        return questionRepository.findAll(PageRequest.of(page, size,
                Sort.by("questionId").descending()));
    }*/

    public List<Question> findQuestions() {
        return questionRepository.findAll();
    }

    public void deleteQuestion(long questionId) {
        Question findQuestion = findVerifiedQuestion(questionId);
        questionRepository.delete(findQuestion);
    }

    public Question findVerifiedQuestion(long questionId) {
        Optional<Question> optionalQuestion =
                questionRepository.findById(questionId);
        Question findQuestion =
                optionalQuestion.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
        return findQuestion;
    }

    private Question findVerifiedQuestionByQuery(long questionId) {
        Optional<Question> optionalQuestion = questionRepository.findByQuestion(questionId);
        Question findQuestion =
                optionalQuestion.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        return findQuestion;
    }

}
