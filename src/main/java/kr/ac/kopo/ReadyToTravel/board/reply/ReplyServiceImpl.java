package kr.ac.kopo.ReadyToTravel.board.reply;

import kr.ac.kopo.ReadyToTravel.dto.ReplyDTO;
import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;
import kr.ac.kopo.ReadyToTravel.entity.board.BoardEntity;
import kr.ac.kopo.ReadyToTravel.entity.board.ReplyEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReplyServiceImpl implements ReplyService {
    final private ReplyRepository replyRepository;
    public ReplyServiceImpl(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }
    @Override
    public void addReply(ReplyDTO reply) {
        ReplyEntity replyEntity = ReplyEntity.builder()
                .replyNum(reply.getReplyNum())
                .member(MemberEntity.builder().num(reply.getWriter()).build())
                .writeDate(new Date())
                .content(reply.getContent())
                .boardEntity(BoardEntity.builder().boardNum(reply.getBoardNum()).build())
                .build();
        replyRepository.save(replyEntity);
    }

    @Override
    @Transactional
    public void removeReply(long boardNum, long replyNum) {
        replyRepository.deleteByBoardEntityBoardNumAndReplyNum(boardNum, replyNum);
    }



    @Override
    @Transactional
    public void updateReply(ReplyDTO dto) {
        ReplyEntity entity = replyRepository.findById(dto.getReplyNum())
                .orElseThrow(() -> new NullPointerException("존재 하지 않는 댓글"));

        entity.setContent(dto.getContent());
    }


}
