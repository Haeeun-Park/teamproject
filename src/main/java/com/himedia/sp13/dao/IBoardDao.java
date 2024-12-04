package com.himedia.sp13.dao;

import com.himedia.sp13.dto.BoardDto;
import com.himedia.sp13.dto.Paging;
import com.himedia.sp13.dto.ReplyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IBoardDao {

    List<BoardDto> selectBoard(Paging paging);

    int getAllCount();

    void insert(BoardDto boarddto);

    BoardDto selectBoardOne(int num);

    List<ReplyDto> selectReply(int num);

    void plusReadcount(int num);

    void insertReply(ReplyDto replydto);


    void deleteReply(int replynum);

    void updateBoard(BoardDto boarddto);

    void deleteBoard(int num);

    int getReplyCount(int num);
}
