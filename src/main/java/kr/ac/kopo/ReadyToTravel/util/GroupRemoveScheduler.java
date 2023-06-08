//package kr.ac.kopo.ReadyToTravel.util;
//
//import kr.ac.kopo.ReadyToTravel.entity.group.GroupMembership;
//import kr.ac.kopo.ReadyToTravel.group.GroupCustomRepository;
//import kr.ac.kopo.ReadyToTravel.group.GroupMembershipRepository;
//import kr.ac.kopo.ReadyToTravel.group.GroupRepository;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//
//import javax.transaction.Transactional;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@EnableScheduling
//@Configuration
//public class GroupRemoveScheduler {
//    final GroupCustomRepository groupCustomRepository;
//    final GroupMembershipRepository groupMembershipRepository;
//    final GroupRepository groupRepository;
//
//    public GroupRemoveScheduler(GroupCustomRepository groupCustomRepository, GroupMembershipRepository groupMembershipRepository, GroupRepository groupRepository) {
//        this.groupCustomRepository = groupCustomRepository;
//        this.groupMembershipRepository = groupMembershipRepository;
//        this.groupRepository = groupRepository;
//    }
//
//
//
//    //todo 최종 수정 해야함 그룹 / 그룹멤버십 / 플랜 조인 후
//    @Transactional
//    @Async
//    @Scheduled(cron = "0 0 0 * *")
//    public void groupAutoRemover() {
//        List<GroupMembership> expiredMemberships = groupCustomRepository.expiredGroup();
//        groupMembershipRepository.deleteAll(expiredMemberships);
//
//        Set<Long> expiredGroupNumbers = new HashSet<>();
//        for (GroupMembership membership : expiredMemberships) {
//            expiredGroupNumbers.add(membership.getGroup().getGroupNum());
//        }
//
//        for (Long groupNumber : expiredGroupNumbers) {
//            System.out.println("Expired Group Number: " + groupNumber);
//
//        }
//    }
//}