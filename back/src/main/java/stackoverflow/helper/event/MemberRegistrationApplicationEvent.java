package stackoverflow.helper.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import stackoverflow.member.entity.Member;
@Getter
public class MemberRegistrationApplicationEvent {
    private Member member;
    public MemberRegistrationApplicationEvent(Member member) {
        this.member = member;
    }
}

