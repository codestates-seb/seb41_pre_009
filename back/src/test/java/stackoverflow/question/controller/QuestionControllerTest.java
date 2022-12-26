package stackoverflow.question.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.UriComponentsBuilder;
import stackoverflow.answer.dto.AnswerDto;
import stackoverflow.answer.entity.Answer;
import stackoverflow.answer.mapper.AnswerMapper;
import stackoverflow.answer.service.AnswerService;
import stackoverflow.member.entity.Member;
import stackoverflow.question.dto.QuestionDto;
import stackoverflow.question.entity.Question;
import stackoverflow.question.mapper.QuestionMapper;
import stackoverflow.question.service.QuestionService;

import java.net.URI;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class QuestionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private QuestionService questionService;

    @MockBean
    private QuestionMapper mapper;

    @Test
    void postQuestionTest() throws Exception {
        //given
        Member member = new Member("hgd@gmail.com",
                "홍길동",
                "1234");

        QuestionDto.Post post = new QuestionDto.Post(1L,
                "Algorithm to simplify a weighted directed graph of debts",
                "I've been using a little python script I wrote to manage debt amongst my roommates.");
        QuestionDto.Response responseBody = new QuestionDto.Response(1L,
                1L,
                "Algorithm to simplify a weighted directed graph of debts",
                "I've been using a little python script I wrote to manage debt amongst my roommates.",
                0,
                Question.QuestionStatus.QUESTION_ACCEPTED);
        // Stubbing by Mockito
        given(mapper.questionPostDtoToQuestion(Mockito.any(QuestionDto.Post.class))).willReturn(new Question());

        given(questionService.createQuestion(Mockito.any(Question.class))).willReturn(new Question());

        given(mapper.questionToQuestionResponseDto(Mockito.any(Question.class))).willReturn(responseBody);

        String content = gson.toJson(post);
        URI uri = UriComponentsBuilder.newInstance().path("/questions").build().toUri();

        // when
        ResultActions actions =
                mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(uri)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content));
        // then
        MvcResult result = actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.memberId").value(post.getMemberId()))
                .andExpect(jsonPath("$.data.title").value(post.getTitle()))
                .andExpect(jsonPath("$.data.content").value(post.getContent()))
                .andReturn();

    }
    @Test
    void patchQuestionTest() throws Exception {
        // given
        long questionId = 1L;

        QuestionDto.Patch patch = new QuestionDto.Patch(1L,
                "Patch:Algorithm to simplify a weighted directed graph of debts",
                "Patch:I've been using a little python script I wrote to manage debt amongst my roommates.",
                Question.QuestionStatus.QUESTION_ACCEPTED);

        QuestionDto.Response response = new QuestionDto.Response(1L,
                1L,
                "Patch:Algorithm to simplify a weighted directed graph of debts",
                "Patch:I've been using a little python script I wrote to manage debt amongst my roommates.",
                0,
                Question.QuestionStatus.QUESTION_ACCEPTED);

        // Stubbing by Mockito
        given(mapper.questionPatchDtoToQuestion(Mockito.any(QuestionDto.Patch.class))).willReturn(new Question());

        given(questionService.updateQuestion(Mockito.any(Question.class))).willReturn(new Question());

        given(mapper.questionToQuestionResponseDto(Mockito.any(Question.class))).willReturn(response);

        Gson gson = new Gson();
        String content = gson.toJson(patch);

        URI uri = UriComponentsBuilder.newInstance().path("/questions/{question-id}").buildAndExpand(questionId).toUri();

        // when
        ResultActions actions =
                mockMvc.perform(
                        MockMvcRequestBuilders
                                .patch(uri)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.title").value(patch.getTitle()))
                .andExpect(jsonPath("$.data.content").value(patch.getContent()));
    }

    @Test
    void getQuestionTest() throws Exception {
        // given
        long questionId = 1L;
        Question question = new Question("Algorithm to simplify a weighted directed graph of debts",
                "I've been using a little python script I wrote to manage debt amongst my roommates.",
                0);
        question.setQuestionId(questionId);

        QuestionDto.Response response = new QuestionDto.Response(1L,
                1L,
                "Algorithm to simplify a weighted directed graph of debts",
                "I've been using a little python script I wrote to manage debt amongst my roommates.",
                1,
                Question.QuestionStatus.QUESTION_ACCEPTED);

        // Stubbing by Mockito
        given(questionService.findQuestion(Mockito.anyLong())).willReturn(new Question());
        given(mapper.questionToQuestionResponseDto(Mockito.any(Question.class))).willReturn(response);

        URI uri = UriComponentsBuilder.newInstance().path("/questions/{question-id}").buildAndExpand(questionId).toUri();

        // when
        ResultActions actions = mockMvc.perform(
                MockMvcRequestBuilders
                        .get(uri)
                        .accept(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.title").value(question.getTitle()))
                .andExpect(jsonPath("$.data.content").value(question.getContent()));
    }

    @Test
    void deleteQuestionTest() throws Exception {
        // given
        long questionId = 1L;

        // Stubbing by Mockito
        doNothing().when(questionService).deleteQuestion(questionId);

        // when
        ResultActions actions = mockMvc.perform(delete("/questions/" + questionId));

        // then
        actions.andExpect(status().isNoContent());
    }

}
