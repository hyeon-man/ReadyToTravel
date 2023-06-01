package kr.ac.kopo.ReadyToTravel.entity.attach;

import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;
import kr.ac.kopo.ReadyToTravel.entity.board.BoardEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Member;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member_attach")
@Getter
@Setter
@ToString
public class MemberAttachEntity extends Attach{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_attach_num")
    private Long memberAttachNum;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_num")
    @NotNull
    private MemberEntity memberEntity;

}
