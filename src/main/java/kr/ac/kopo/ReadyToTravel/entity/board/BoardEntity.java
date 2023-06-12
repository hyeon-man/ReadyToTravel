package kr.ac.kopo.ReadyToTravel.entity.board;

import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;
import kr.ac.kopo.ReadyToTravel.entity.attach.Attach;
import kr.ac.kopo.ReadyToTravel.entity.attach.BoardAttachEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter @Setter
@ToString
@Table(name = "board")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_num")
    private Long boardNum;

    @Column
    private String boardName;

    @Column
    private String boardContent;

    @Column
    private Date boardDateCreate;


    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "boardWrite")
    private MemberEntity boardWriter;

    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.ALL)
    private List<ReplyEntity> replies;

    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.ALL)
    private List<BoardAttachEntity> attachEntities = new ArrayList<>();

}
