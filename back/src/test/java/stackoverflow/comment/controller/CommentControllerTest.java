/*
package stackoverflow.comment.controller;


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
import stackoverflow.comment.dto.CommentDto;
import stackoverflow.comment.entity.Comment;
import stackoverflow.comment.mapper.CommentMapper;
import stackoverflow.comment.service.CommentService;
import stackoverflow.member.entity.Member;

import java.net.URI;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private CommentService commentService;

    @MockBean
    private CommentMapper mapper;

    @Test
    void postCommentTest() throws Exception {
        // given
        Member member = new Member("hgd@gmail.com",
                "홍길동",
                "1234");

        CommentDto.Post post = new CommentDto.Post( 1L,"홍길동은 아버지를 아버지라 부르지 못했다.");
        CommentDto.Response responseBody = new CommentDto.Response(1L,
                1L,"홍길동은 아버지를 아버지라 부르지 못했다.");

        // Stubbing by Mockito
        given(mapper.commentPostDtoToComment(Mockito.any(CommentDto.Post.class))).willReturn(new Comment());

        given(commentService.createComment(Mockito.any(Comment.class))).willReturn(new Comment());

        given(mapper.commentToCommentResponseDto(Mockito.any(Comment.class))).willReturn(responseBody);

        Gson gson = new Gson();
        String content = gson.toJson(post);
        URI uri = UriComponentsBuilder.newInstance().path("/comments").build().toUri();

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
                .andExpect(jsonPath("$.data.text").value(post.getText()))
                .andReturn();

//        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void patchCommentTest() throws Exception {
        // given
        long commentId = 1L;

        CommentDto.Patch patch = new CommentDto.Patch(0,
                1L,"홍길동은 아버지를 아버지라 부르지 못했다. 어머니는 어머니라 불렀을까? 형은 뭐라고 불렀을까?");

        CommentDto.Response response = new CommentDto.Response(1L,
                1L,"홍길동은 아버지를 아버지라 부르지 못했다. 어머니는 어머니라 불렀을까? 형은 뭐라고 불렀을까?");

        // Stubbing by Mockito
        given(mapper.commentPatchDtoToComment(Mockito.any(CommentDto.Patch.class))).willReturn(new Comment());

        given(commentService.updateComment(Mockito.any(Comment.class))).willReturn(new Comment());

        given(mapper.commentToCommentResponseDto(Mockito.any(Comment.class))).willReturn(response);

        Gson gson = new Gson();
        String content = gson.toJson(patch);

        URI uri = UriComponentsBuilder.newInstance().path("/comments/{commentId}").buildAndExpand(commentId).toUri();

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
                .andExpect(jsonPath("$.data.text").value(patch.getText()));
    }

    @Test
    void getcommentTest() throws Exception {
        // given
        long commentId = 1L;
        Comment comment = new Comment("홍길동");
        comment.setCommentId(commentId);

        CommentDto.Response response = new CommentDto.Response(1L,
                1L,"홍길동");
        // Stubbing by Mockito
        given(commentService.findComment(Mockito.anyLong())).willReturn(new Comment());
        given(mapper.commentToCommentResponseDto(Mockito.any(Comment.class))).willReturn(response);

        URI uri = UriComponentsBuilder.newInstance().path("/comments/{commentId}").buildAndExpand(commentId).toUri();

        // when
        ResultActions actions = mockMvc.perform(
                MockMvcRequestBuilders
                        .get(uri)
                        .accept(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.text").value(comment.getText()));
    }

    @Test
    void deleteCommentTest() throws Exception {
        // given
        long commentId = 1L;

        // Stubbing by Mockito
        doNothing().when(commentService).deleteComment(commentId);

        // when
        ResultActions actions = mockMvc.perform(delete("/comments/" + commentId));

        // then
        actions.andExpect(status().isNoContent());
    }
}
*/