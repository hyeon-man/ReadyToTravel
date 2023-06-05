package kr.ac.kopo.ReadyToTravel.member;

import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;

public interface MemberService {
    boolean checkId(String id);

    void singUp(MemberDTO memberDTO);

    void removeMember(Long num);

    MemberDTO login(MemberDTO memberDTO);

    boolean initPass(String id, String email) throws MessagingException;

    boolean sendEmailCode(String email);

    boolean validateCode(String email, String mailValidateKey);

    void updateName(MemberDTO memberDTO, String name);

    boolean updatePassword(MemberDTO memberDTO, String password);

    void addAttach(Long num, MultipartFile attach);

}
