package kr.ac.kopo.ReadyToTravel.entity.attach;

import javax.persistence.*;

@Entity(name = "group_chat_attach")
public class GroupChatAttachEntity extends Attach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupNum;

}
