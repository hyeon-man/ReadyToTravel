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

}
