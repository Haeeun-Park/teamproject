package com.himedia.sp13.controller;

import com.himedia.sp13.dto.BoardDto;
import com.himedia.sp13.dto.MemberDto;
import com.himedia.sp13.dto.Paging;
import com.himedia.sp13.dto.ReplyDto;
import com.himedia.sp13.service.BoardService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

@Controller
public class BoardController {
    @Autowired
    BoardService bs;

    @Autowired
    ServletContext context;

    @GetMapping("/main")
    public ModelAndView main(HttpServletRequest request, Model model) {

       ModelAndView mav = new ModelAndView();
       HashMap<String,Object> result = bs.selectBoard(request);

        mav.addObject("boardList", result.get("boardList"));
        mav.addObject("paging", result.get("paging"));

        mav.setViewName("board/main");
        return mav;
    }

    @GetMapping("/insertBoardForm")
    public String insertBoardForm(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        MemberDto dto = (MemberDto)session.getAttribute("loginUser");
        model.addAttribute("loginUser", dto);
        return "board/insertBoard";
    }

    @GetMapping("/selectimg")
    public String selectimg(HttpServletRequest request, Model model) {
        return "board/selectimg";
    }


    @PostMapping("/fileupload")
    public String fileupload(HttpServletRequest request, Model model, @RequestParam("image") MultipartFile file) {

        // HttpSession session = request.getSession();
        // ServletContext context = session.getServletContext();
        String path = context.getRealPath("/upload");
        String filename = file.getOriginalFilename();
        String savefilename = makeSavefilename(filename); // 저장 파일 이름 생성 함수 호출

        String uploadPath = path + "/" + savefilename;

        try {
            file.transferTo(new File(uploadPath)); // 파일 업로드
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        model.addAttribute("image",filename);
        model.addAttribute("savefilename",savefilename);

        return "board/completeUpload";
    }

    private String makeSavefilename(String filename) {
        Calendar today = Calendar.getInstance();
        long t = today.getTimeInMillis();
        String fn1 = filename.substring(0, filename.indexOf(".")); // abc.jsp -> abc
        String fn2 = filename.substring(filename.indexOf(".") + 1); // abc.jsp ->jsp
        return fn1 + "_" + fn2;
    }

    @GetMapping("/insertBoardForm2")
    public String insertBoardForm2(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        MemberDto dto = (MemberDto)session.getAttribute("loginUser");
        model.addAttribute("loginUser", dto);
        return "board/insertBoard2";
    }


    @PostMapping("/insertBoard2")
    public String insertBoard2(@ModelAttribute("dto") @Valid BoardDto boarddto, BindingResult result, @RequestParam("image") MultipartFile file, HttpServletRequest request, Model model ) {

        String url="board/insertBoard2";
        if(result.getFieldError("pass")!=null){
            model.addAttribute("pass",result.getFieldError("pass").getDefaultMessage());
        }else if(result.getFieldError("title")!=null){
            model.addAttribute("title",result.getFieldError("title").getDefaultMessage());
        }else if(result.getFieldError("content")!=null){
            model.addAttribute("content",result.getFieldError("content").getDefaultMessage());
        }else {


            String path = context.getRealPath("/upload");
            Calendar today = Calendar.getInstance();
            long t = today.getTimeInMillis();
            String filename = file.getOriginalFilename(); // MultipartFile file 에 전송된 선택 파일 이름 추출
            String fn1 = filename.substring(0, filename.indexOf(".")); // abc.jsp -> abc
            String fn2 = filename.substring(filename.indexOf(".") + 1); // abc.jsp ->jsp
            String uploadPath = path + "/" + fn1 + t + "." + fn2;

            try {
                file.transferTo(new File(uploadPath)); // 파일 업로드
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            boarddto.setImage(filename);
            boarddto.setSavefilename(fn1 + t + "." + fn2);
            bs.insert(boarddto);
            url = "redirect:/main";
        }
        return url;
    }

    @PostMapping("/insertBoard")
    public String insertBoard(@ModelAttribute("dto") @Valid BoardDto boarddto, BindingResult result, HttpServletRequest request, Model model) {
        String url="board/insertBoard";
        if(result.getFieldError("pass")!=null){
            model.addAttribute("msg",result.getFieldError("pass").getDefaultMessage());
        }else if(result.getFieldError("title")!=null){
            model.addAttribute("msg",result.getFieldError("title").getDefaultMessage());
        }else if(result.getFieldError("content")!=null){
            model.addAttribute("msg",result.getFieldError("content").getDefaultMessage());
        }else {
            bs.insert(boarddto);
            url = "redirect:/main";
        }
        return url;
    }

    @GetMapping("/boardView")
    public ModelAndView boardView(@RequestParam("num") int num, HttpServletRequest request, Model model) {
        ModelAndView mav = new ModelAndView();
        HashMap<String,Object> result = bs.getBoard(num);
        mav.addObject("board", result.get("board"));
        mav.addObject("replyList", result.get("replyList"));
        mav.setViewName("board/boardView");
        return mav;
    }

    @PostMapping("/addReply")
    public ModelAndView addReply(ReplyDto replydto) {
        ModelAndView mav = new ModelAndView();
        HashMap<String,Object> result = bs.insertReply(replydto);
        mav.addObject("board", result.get("board"));
        mav.addObject("replyList", result.get("replyList"));
        mav.setViewName("board/boardView");
        return mav;
    }

    @GetMapping("/deleteReply")
    public ModelAndView deleteReply(ReplyDto replydto) {
        ModelAndView mav = new ModelAndView();

        HashMap<String,Object> result = bs.deleteReply(replydto);
        mav.addObject("board", result.get("board"));
        mav.addObject("replyList", result.get("replyList"));
        mav.setViewName("board/boardView");
        return mav;
    }

    @GetMapping("/updateBoardForm")
    public ModelAndView updateBoardForm(@RequestParam("num") int num) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("dto", bs.getBoardupdate(num));
        mav.setViewName("board/updateBoard");
        return mav;
    }

    @PostMapping("/updateBoard")
    public String updateBoard(@ModelAttribute("dto") @Valid BoardDto boarddto, BindingResult result,Model model) {
        String url="board/updateBoard";

        BoardDto bdto = bs.getBoardupdate(boarddto.getNum());
        if(!bdto.getPass().equals(boarddto.getPass()))
            model.addAttribute("msg","비밀번호가 일치하지 않습니다");
        else if(result.getFieldError("pass")!=null){
            model.addAttribute("msg",result.getFieldError("pass").getDefaultMessage());
        }else if(result.getFieldError("title")!=null){
            model.addAttribute("msg",result.getFieldError("title").getDefaultMessage());
        }else if(result.getFieldError("content")!=null){
            model.addAttribute("msg",result.getFieldError("content").getDefaultMessage());
        }else {
            bs.updateBoard(boarddto);
            url="redirect:/boardViewWoc?num=" + boarddto.getNum();
            }
        return url;
    }

    @GetMapping("/boardViewWoc")
    public ModelAndView boardViewWoc(@RequestParam("num") int num) {
        ModelAndView mav = new ModelAndView();
        HashMap<String,Object> result = bs.getboardViewWoc(num);
        mav.addObject("board", result.get("board"));
        mav.addObject("replyList", result.get("replyList"));
        mav.setViewName("board/boardView");
        return mav;
    }

    @GetMapping("/checkPass")
    public ModelAndView checkPass(@RequestParam("num") int num) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("num",num);
        mav.setViewName("board/checkPass");
        return mav;
    }

    @PostMapping("/confirmDeletePass")
    public ModelAndView confirmDeletePass(
            @RequestParam("num") int num,
            @RequestParam("pass") String pass) {

        ModelAndView mav = new ModelAndView();
        BoardDto bdto = bs.getBoardupdate(num);
        if(bdto.getPass().equals(pass)) mav.addObject("result",1);
        else mav.addObject("result",-1);
        mav.addObject("num",num);
        mav.setViewName("board/delete");
        return mav;
    }

    @GetMapping("/deleteBoard")
    public String deleteBoard(@RequestParam("num") int num) {
        bs.deleteBoard(num);
        return "redirect:/main";
    }

}

