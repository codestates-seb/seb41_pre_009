package stackoverflow.member.mapper;

import org.mapstruct.Mapper;
import stackoverflow.member.dto.MemberDto;
import stackoverflow.member.entity.Member;
import java.util.List;


@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostDtoToMember(MemberDto.Post requestBody);
    Member memberPatchDtoToMember(MemberDto.Patch requestBody);
    MemberDto.Response memberToMemberResponseDto(Member member);
    List<MemberDto.Response> membersToMemberResponseDtos(List<Member> members);
}
