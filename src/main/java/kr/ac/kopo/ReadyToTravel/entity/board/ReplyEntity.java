package kr.ac.kopo.ReadyToTravel.entity.board;


import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity(name = "reply")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyEntity {

    @Id
    @Column(name = "reply_num")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyNum;

    @Column(nullable = false)
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date writeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_num", nullable = false)
    private BoardEntity boardEntity;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "writer")
    private MemberEntity member;
}
