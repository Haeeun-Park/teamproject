package com.himedia.sp13.service;

import com.himedia.sp13.dao.IBoardDao;
import com.himedia.sp13.dto.BoardDto;
import com.himedia.sp13.dto.Paging;
import com.himedia.sp13.dto.ReplyDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class BoardService {
    @Autowired
    IBoardDao bdao;

    public HashMap<String,Object> selectBoard(HttpServletRequest request) {
        HashMap<String,Object> result=new HashMap<>();
        HttpSession session=request.getSession();
        int page=1;
        if(request.getParameter("page")!=null){
            page=Integer.parseInt(request.getParameter("page"));
            session.setAttribute("page",page);
        }else if(session.getAttribute("page")!=null){
            page=Integer.parseInt((String)session.getAttribute("page"));
        }

        Paging paging = new Paging();
        paging.setPage(page);
        int count = bdao.getAllCount();
        paging.setTotalCount(count);
        paging.calPaging();
        List<BoardDto> list = bdao.selectBoard(paging);

        for(BoardDto board : list){
            int replycnt=bdao.getReplyCount(board.getNum());
            board.setReplycnt(replycnt);
        }

        result.put("boardList",list);
        result.put("paging",paging);
        return result;
    }

    public List<BoardDto> selectBoard(Paging paging) {
        List<BoardDto> lsit=bdao.selectBoard(paging);
        return lsit;
    }

    public int getAllCount() {
        int count=bdao.getAllCount();
        return count;
    }

    public void insert(BoardDto boarddto) {
        bdao.insert(boarddto);
    }

    public HashMap<String, Object> getBoard(int num) {
        HashMap<String, Object> result = new HashMap<>();
        bdao.plusReadcount(num);

        BoardDto bdto = bdao.selectBoardOne(num);
        result.put("board", bdto);

        List<ReplyDto> list = bdao.selectReply(num);
        result.put("replyList", list);

        return result;
    }

    public HashMap<String, Object> insertReply(ReplyDto replydto) {
        HashMap<String, Object> result = new HashMap<>();
        bdao.insertReply(replydto);

        BoardDto bdto = bdao.selectBoardOne(replydto.getBoardnum());
        result.put("board", bdto);

        List<ReplyDto> list = bdao.selectReply(replydto.getBoardnum());
        result.put("replyList", list);

        return result;
    }

    public HashMap<String, Object> deleteReply(ReplyDto replydto) {
        HashMap<String, Object> result = new HashMap<>();
        bdao.deleteReply(replydto.getReplynum());

        BoardDto bdto = bdao.selectBoardOne(replydto.getBoardnum());
        result.put("board", bdto);

        List<ReplyDto> list = bdao.selectReply(replydto.getBoardnum());
        result.put("replyList", list);

        return result;
    }

    public BoardDto getBoardupdate(int num){
        BoardDto bdto = bdao.selectBoardOne(num);
        return bdto;
    }

    public void updateBoard(BoardDto boarddto) {
        bdao.updateBoard(boarddto);
    }

    public HashMap<String, Object> getboardViewWoc(int num) {
        HashMap<String, Object> result = new HashMap<>();


        BoardDto bdto = bdao.selectBoardOne(num);
        result.put("board", bdto);

        List<ReplyDto> list = bdao.selectReply(num);
        result.put("replyList", list);

        return result;
    }

    public void deleteBoard(int num) {
        bdao.deleteBoard(num);
    }
}
