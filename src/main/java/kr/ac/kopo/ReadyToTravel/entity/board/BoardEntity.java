package kr.ac.kopo.ReadyToTravel.entity.board;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "board")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardEntity {

    @Id
    @Column(name = "board_num")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long boardNum;

}
