package kr.ac.kopo.ReadyToTravel.entity.board;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "reply")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyEntity {

    @Id
    @Column(name = "reply_num")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long replyNum;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_num", nullable = false)
    private BoardEntity boardEntity;
}
