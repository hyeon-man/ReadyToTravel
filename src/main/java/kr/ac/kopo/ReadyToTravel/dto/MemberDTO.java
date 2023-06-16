package kr.ac.kopo.ReadyToTravel.dto;


import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
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

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Size(max = 16, message = "아이디는 16자 까지 가능합니다.")
    private String memberId;

    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    @Size(min = 2, max = 8, message = "닉네임은 2-8 자 까지 가능합니다.")
    private String name;

    @NotBlank(message = "패스워드는 필수 입력 값입니다.")
    @Size(min = 8, max = 16, message = "패스워드는 8-16 자 까지 가능합니다.")
    private String password;

    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    private String profileIMG;

    private Date signupDate;

    private String phoneNum;


    private MultipartFile profileFile;


}
