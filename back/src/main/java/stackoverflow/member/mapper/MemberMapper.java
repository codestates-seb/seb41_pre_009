package stackoverflow.member.mapper;

import org.mapstruct.Mapper;
import stackoverflow.member.dto.MemberDto;
import stackoverflow.member.entity.Member;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostDtoToMember(MemberDto.Post requestBody);
    Member memberPatchDtoToMember(MemberDto.Patch requestBody);

    MemberDto.Response memberToMemberResponseDto(Member member);
}
