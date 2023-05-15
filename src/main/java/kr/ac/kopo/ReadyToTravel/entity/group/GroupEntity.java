package kr.ac.kopo.ReadyToTravel.entity.group;

import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;
import lombok.*;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "plan_group")
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupNum;

    private String name;

    private Date createDate;

    private Date modifiedDate;




}
