package stackoverflow.answer.controller;


import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
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

import java.net.URI;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AnswerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private AnswerService answerService;

    @MockBean
    private AnswerMapper mapper;

    @Test
    void postAnswerTest() throws Exception {
        // given
        Member member = new Member("hgd@gmail.com",
                "홍길동",
                "1234");

        AnswerDto.Post post = new AnswerDto.Post(1L,1, "홍길동은 아버지를 아버지라 부르지 못했다. 어머니는 어머니라 불렀을까? 형은 뭐라고 불렀을까?");
        AnswerDto.Response responseBody = new AnswerDto.Response(1L,
                1L,
                1L,"홍길동은 아버지를 아버지라 부르지 못했다. 어머니는 어머니라 불렀을까? 형은 뭐라고 불렀을까?");

        // Stubbing by Mockito
        given(mapper.answerPostDtoToAnswer(Mockito.any(AnswerDto.Post.class))).willReturn(new Answer());

        given(answerService.createAnswer(Mockito.any(Answer.class))).willReturn(new Answer());

        given(mapper.answerToAnswerResponseDto(Mockito.any(Answer.class))).willReturn(responseBody);

        String content = gson.toJson(post);
        URI uri = UriComponentsBuilder.newInstance().path("/answers").build().toUri();

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
                .andExpect(jsonPath("$.data.questionId").value(post.getQuestionId()))
                .andExpect(jsonPath("$.data.content").value(post.getContent()))
                .andReturn();

//        System.out.println(result.getResponse().getContentAsString());
    }

@Test
    void patchAnswerTest() throws Exception {
        // given
        long answerId = 1L;

    AnswerDto.Patch patch = new AnswerDto.Patch(0,
            "홍길동은 아버지를 아버지라 부르지 못했다. 어머니는 어머니라 불렀을까? 형은 뭐라고 불렀을까?");

    AnswerDto.Response response = new AnswerDto.Response(1L,
            1L,
            1L,"홍길동은 아버지를 아버지라 부르지 못했다. 어머니는 어머니라 불렀을까? 형은 뭐라고 불렀을까?");

        // Stubbing by Mockito
        given(mapper.answerPatchDtoToAnswer(Mockito.any(AnswerDto.Patch.class))).willReturn(new Answer());

        given(answerService.updateAnswer(Mockito.any(Answer.class))).willReturn(new Answer());

        given(mapper.answerToAnswerResponseDto(Mockito.any(Answer.class))).willReturn(response);

        Gson gson = new Gson();
        String content = gson.toJson(patch);

        URI uri = UriComponentsBuilder.newInstance().path("/answers/{answerId}").buildAndExpand(answerId).toUri();

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
                .andExpect(jsonPath("$.data.content").value(patch.getContent()));
    }

    @Test
    void getAnswerTest() throws Exception {
        // given
        long answerId = 1L;
        Answer answer = new Answer("홍길동");
        answer.setAnswerId(answerId);

        AnswerDto.Response response = new AnswerDto.Response(1L,
                1L,
                1L,"홍길동");
        // Stubbing by Mockito
        given(answerService.findAnswer(Mockito.anyLong())).willReturn(new Answer());
        given(mapper.answerToAnswerResponseDto(Mockito.any(Answer.class))).willReturn(response);

        URI uri = UriComponentsBuilder.newInstance().path("/answers/{answerId}").buildAndExpand(answerId).toUri();

        // when
        ResultActions actions = mockMvc.perform(
                MockMvcRequestBuilders
                        .get(uri)
                        .accept(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.content").value(answer.getContent()));
    }

    @Test
    void deleteAnswerTest() throws Exception {
        // given
        long answerId = 1L;

        // Stubbing by Mockito
        doNothing().when(answerService).deleteAnswer(answerId);

        // when
        ResultActions actions = mockMvc.perform(delete("/answers/" + answerId));

        // then
        actions.andExpect(status().isNoContent());
    }
}
