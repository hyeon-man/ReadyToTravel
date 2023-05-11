package kr.ac.kopo.ReadyToTravel.entity.group;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "invite_url")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InviteEntity {
    @Id
    @Column(name = "invite_url_num")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Num;

    private String inviteURL;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_num")
    private GroupEntity groupEntity;

}
