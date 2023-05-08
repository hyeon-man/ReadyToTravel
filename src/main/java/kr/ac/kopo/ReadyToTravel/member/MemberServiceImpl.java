package kr.ac.kopo.ReadyToTravel.member;

import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;
import kr.ac.kopo.ReadyToTravel.util.GenerateTemporaryPassword;
import kr.ac.kopo.ReadyToTravel.util.PassEncode;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {
    final MemberRepository repository;
    private final GenerateTemporaryPassword generateTemporaryPassword;
    public MemberServiceImpl(MemberRepository repository, GenerateTemporaryPassword generateTemporaryPassword) {
        this.repository = repository;
        this.generateTemporaryPassword = generateTemporaryPassword;
    }


    @Override
    public String checkId(String id) {
        MemberEntity entity = repository.findAllByMemberId(id);
        if (entity == null) {
            return "사용 가능";
        } else {
            return "사용 불가";
        }
    }

    @Override
    public void singUp(MemberDTO memberDTO) {
        // Convert DTO to Entity
        MemberEntity entity = memberDTO.convertToEntity(memberDTO);

        // Encode password to SHA-512
        String encodedPassword = PassEncode.encode(entity.getPassword());
        entity.setPassword(encodedPassword);

        // Save entity
        repository.save(entity);
    }

    @Override
    public void removeMember(Long num) {
        Optional<MemberEntity> optionalMemberEntity = repository.findById(num);

        if (optionalMemberEntity.isPresent()) {
            repository.deleteById(num);
            System.out.println("지움");
        } else {
            System.out.println(num + "에 해당하는 회원이 없습니다");
        }
    }

    @Override
    public boolean login(MemberDTO memberDTO) {
        MemberEntity memberEntity = memberDTO.convertToEntity(memberDTO);

        String id = memberEntity.getMemberId();
        String pass = PassEncode.encode(memberEntity.getPassword());

        return repository.existsByMemberIdAndPassword(id, pass);

    }


    @Override
    public void initPass(String email) {
        Optional<MemberEntity> optionalMember = repository.findByEmail(email);

        if (optionalMember.isPresent()) {
            MemberEntity member = optionalMember.get();
            String newPassword = GenerateTemporaryPassword.generateTemporaryPassword();
            member.setPassword(newPassword);
            repository.save(member);
        }//else는 뭐라 해야하지.... 고민 해봐야함 optinal 말고 throw를 해야하나...?
    }

}
