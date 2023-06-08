package kr.ac.kopo.ReadyToTravel.dto;


import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
//todo validate 작성 !
public class MemberDTO {

    private Long num;

    // 아이디
    private String memberId;

    //이름
    private String name;

    //비밀번호
    private String password;

    //프로필 사진
    private String profileIMG;

    //가입 일자
    private Date signupDate;

    //이메일
    @Email
    private String email;

    //핸드폰 번호
    private String phoneNum;

    private MultipartFile profileFile;

    public  MemberEntity convertToEntity(MemberDTO dto) {
        return MemberEntity.builder()
                .memberId(dto.getMemberId())
                .password(dto.getPassword())
                .name(dto.getName())
                .profileIMG(dto.getProfileIMG())
                .signupDate(dto.getSignupDate())
                .email(dto.getEmail())
                .phoneNum(dto.getPhoneNum())
                .build();
    }

}
