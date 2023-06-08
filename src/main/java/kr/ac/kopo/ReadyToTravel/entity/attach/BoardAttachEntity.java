package kr.ac.kopo.ReadyToTravel.entity.attach;

import kr.ac.kopo.ReadyToTravel.entity.board.BoardEntity;
import lombok.*;
import org.springframework.http.ContentDisposition;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "board_attach")
@Getter
@Setter
@ToString
public class BoardAttachEntity extends Attach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_attach_num")
    private Long boardAttachNum;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "board_num")
    @NotNull
    private BoardEntity boardEntity;

}