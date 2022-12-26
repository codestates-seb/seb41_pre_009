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
        AnswerDto.Post post = new AnswerDto.Post("홍길동", 1);
        AnswerDto.Response responseBody = new AnswerDto.Response(1L,
                "홍길동",
                1);

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
                .andExpect(jsonPath("$.data.content").value(post.getContent()))
                .andExpect(jsonPath("$.data.answerVoteCount").value(post.getAnswerVoteCount()))
                .andReturn();

//        System.out.println(result.getResponse().getContentAsString());
    }

@Test
    void patchAnswerTest() throws Exception {
        // given
        long answerId = 1L;

    AnswerDto.Patch patch = new AnswerDto.Patch(0,
            "홍길동",
            1);

    AnswerDto.Response response = new AnswerDto.Response(1L,
            "홍길동",
            1);

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
        Answer answer = new Answer(1L, "홍길동");
        answer.setAnswerId(answerId);

        AnswerDto.Response response = new AnswerDto.Response(1L,
                "홍길동",
                1);

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
                .andExpect(jsonPath("$.data.content").value(answer.getContent()))
                .andExpect(jsonPath("$.data.answerVoteCount").value(answer.getAnswerVoteCount()));
    }

    @Test
    void deleteanswerTest() throws Exception {
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
