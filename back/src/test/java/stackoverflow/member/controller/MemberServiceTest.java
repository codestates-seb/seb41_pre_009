/*
package stackoverflow.member.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import stackoverflow.exception.BusinessLogicException;
import stackoverflow.member.entity.Member;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {
    @Mock
    private memberRepository memberRepository;

    @InjectMocks
    private memberService memberService;

    @Test
    public void cancelmemberTest() {
        // given
        long memberId = 1L;
        Member member = StubData.MockMember.getSingleResponseBody(memberId);

        // Stubbing by Mockito
        given(memberRepository.findById(memberId)).willReturn(Optional.of(member));

        // when
        Executable executable = () -> memberService.cancelMember(memberId);

        // then
        assertThrows(BusinessLogicException.class, executable);
    }
}

}
*/
