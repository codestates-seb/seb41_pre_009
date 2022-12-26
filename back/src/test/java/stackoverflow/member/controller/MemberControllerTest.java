package stackoverflow.member.controller;

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
import stackoverflow.member.dto.MemberDto;
import stackoverflow.member.entity.Member;
import stackoverflow.member.mapper.MemberMapper;
import stackoverflow.member.service.MemberService;

import java.net.URI;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberMapper mapper;

    @Test
    void postMemberTest() throws Exception {
        // given
        MemberDto.Post post = new MemberDto.Post("hgd@gmail.com",
                "홍길동",
                "1234");
        MemberDto.Response responseBody = new MemberDto.Response(1L,
                "hgd@gmail.com",
                "홍길동",
                "1234",
                Member.MemberStatus.MEMBER_ACTIVE);

        // Stubbing by Mockito
        given(mapper.memberPostDtoToMember(Mockito.any(MemberDto.Post.class))).willReturn(new Member());

        given(memberService.createMember(Mockito.any(Member.class))).willReturn(new Member());

        given(mapper.memberToMemberResponseDto(Mockito.any(Member.class))).willReturn(responseBody);

        String content = gson.toJson(post);
        URI uri = UriComponentsBuilder.newInstance().path("/members").build().toUri();

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
                .andExpect(jsonPath("$.data.email").value(post.getEmail()))
                .andExpect(jsonPath("$.data.name").value(post.getName()))
                .andExpect(jsonPath("$.data.password").value(post.getPassword()))
                .andReturn();

//        System.out.println(result.getResponse().getContentAsString());
    }
    @Test
    void patchMemberTest() throws Exception {
        // given
        long memberId = 1L;

        MemberDto.Patch patch = new MemberDto.Patch(0,
                "홍길동",
                "1234",
                Member.MemberStatus.MEMBER_ACTIVE);

        MemberDto.Response response = new MemberDto.Response(1L,
                "hgd@gmail.com",
                "홍길동",
                "1234",
                Member.MemberStatus.MEMBER_ACTIVE);

        // Stubbing by Mockito
        given(mapper.memberPatchDtoToMember(Mockito.any(MemberDto.Patch.class))).willReturn(new Member());

        given(memberService.updateMember(Mockito.any(Member.class))).willReturn(new Member());

        given(mapper.memberToMemberResponseDto(Mockito.any(Member.class))).willReturn(response);

        Gson gson = new Gson();
        String content = gson.toJson(patch);

        URI uri = UriComponentsBuilder.newInstance().path("/members/{memberId}").buildAndExpand(memberId).toUri();

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
                .andExpect(jsonPath("$.data.password").value(patch.getPassword()));
    }

    @Test
    void getMemberTest() throws Exception {
        // given
        long memberId = 1L;
        Member member = new Member("hgd@gmail.com", "홍길동", "1234");
        member.setMemberId(memberId);
        member.setMemberStatus(Member.MemberStatus.MEMBER_ACTIVE);

        MemberDto.Response response = new MemberDto.Response(1L,
                "hgd@gmail.com",
                "홍길동",
                "1234",
                Member.MemberStatus.MEMBER_ACTIVE);

        // Stubbing by Mockito
        given(memberService.findMember(Mockito.anyLong())).willReturn(new Member());
        given(mapper.memberToMemberResponseDto(Mockito.any(Member.class))).willReturn(response);

        URI uri = UriComponentsBuilder.newInstance().path("/members/{memberId}").buildAndExpand(memberId).toUri();

        // when
        ResultActions actions = mockMvc.perform(
                MockMvcRequestBuilders
                        .get(uri)
                        .accept(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.email").value(member.getEmail()))
                .andExpect(jsonPath("$.data.name").value(member.getName()))
                .andExpect(jsonPath("$.data.password").value(member.getPassword()));
    }

    @Test
    void deleteMemberTest() throws Exception {
        // given
        long memberId = 1L;

        // Stubbing by Mockito
        doNothing().when(memberService).deleteMember(memberId);

        // when
        ResultActions actions = mockMvc.perform(delete("/members/" + memberId));

        // then
        actions.andExpect(status().isNoContent());
    }
}