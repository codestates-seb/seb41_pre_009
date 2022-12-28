/*
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
import stackoverflow.comment.entity.Comment;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import stackoverflow.member.entity.Member;


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
        List<Comment> comments = new ArrayList<>();
        AnswerDto.Post post = new AnswerDto.Post(1L, 1L,"안녕하세요 답변 드리겠습니다. 이것은 예시입니다");
        AnswerDto.Response responseBody = new AnswerDto.Response(1L,1L,1L,
                "안녕하세요 답변 드리겠습니다. 이것은 예시입니다",comments);


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
                //.andExpect(jsonPath("$.data.answerVoteCount").value(post.getAnswerVoteCount()))
                .andReturn();
    }

    @Test
    void patchAnswerTest() throws Exception {
        // given
        long answerId = 1L;

        List<Comment> comments = new ArrayList<>();
        AnswerDto.Patch patch = new AnswerDto.Patch(1L,
                "이것은 수정 내용입니다. 이것은 예시입니다.");

        AnswerDto.Response response = new AnswerDto.Response(1L,
                1L,
                1L,
                "이것은 수정 내용입니다. 이것은 예시입니다.",
                comments);


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
                .andExpect(jsonPath("$.data.answerId").value(patch.getAnswerId()))
                .andExpect(jsonPath("$.data.content").value(patch.getContent()));
    }

    @Test
    void getAnswerTest() throws Exception {
        // given
        long answerId = 1L;

        List<Comment> comments = new ArrayList<>();
        Answer answer = new Answer("안녕하세요 답변 드리겠습니다. 이것은 Get 예시입니다");

        answer.setAnswerId(answerId);

        AnswerDto.Response response = new AnswerDto.Response(1L,
                1L,
                1L,
                "안녕하세요 답변 드리겠습니다. 이것은 Get 예시입니다",
                comments);


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
        //.andExpect(jsonPath("$.data.answerVoteCount").value(answer.getAnswerVoteCount()));
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
*/
