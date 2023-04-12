package kr.ac.kopo.ReadyToTravel.entity.group;


import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "group_membership")
public class GroupMembership {

    @Id
    @Column(name = "group_membership_num")
    private Long num;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_num")
    private GroupEntity group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_num")
    private MemberEntity member;


}