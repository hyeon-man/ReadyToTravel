package kr.ac.kopo.ReadyToTravel.entity.group;


import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Member;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
@Table(name = "group_membership")
public class GroupMembership {

    @Id
    @Column(name = "group_membership_num")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_num")
    private GroupEntity group;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "member_num")
    private MemberEntity member;

}