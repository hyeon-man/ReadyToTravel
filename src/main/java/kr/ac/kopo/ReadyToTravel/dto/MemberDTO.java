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

//todo validate 작성 !
public class MemberDTO {

    private Long num;

    // 아이디
    private String memberId;

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


    public static MemberEntity convertToEntity(MemberDTO dto) {
        MemberEntity memberEntity = MemberEntity.builder()
                .memberId(dto.getMemberId())
                .password(dto.getPassword())
                .profileIMG(dto.getProfileIMG())
                .signupDate(dto.getSignupDate())
                .email(dto.getEmail())
                .phoneNum(dto.getPhoneNum())
                .build();

        return memberEntity;
    }
}
