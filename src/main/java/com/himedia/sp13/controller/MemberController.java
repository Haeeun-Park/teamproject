package com.himedia.sp13.controller;

import com.google.gson.Gson;
import com.himedia.sp13.dto.KakaoProfile;
import com.himedia.sp13.dto.MemberDto;
import com.himedia.sp13.dto.OAuthToken;
import com.himedia.sp13.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


@Controller
public class MemberController {

    @Autowired
    MemberService ms; // MemberDao 대신함

    @GetMapping("/")
    public String index() {
        return "member/loginForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("dto") @Valid MemberDto memberdto, BindingResult result, HttpServletRequest request, Model model) {

        String url="member/loginForm";
        if(result.getFieldError("userid")!=null) {
            model.addAttribute("msg", result.getFieldError("userid").getDefaultMessage());
        }else if(result.getFieldError("pwd")!=null) {
            model.addAttribute("msg", result.getFieldError("pwd").getDefaultMessage());
        }else{

            MemberDto mdto = ms.getMember(memberdto.getUserid());
            if(mdto==null) model.addAttribute("msg","아이디 비번 확인");
            else if(!mdto.getPwd().equals(memberdto.getPwd())) model.addAttribute("msg","아이디 비번 확인");
            else{
                HttpSession session = request.getSession();
                session.setAttribute("loginUser",mdto);
                url="redirect:/main";
            }
        }
        return url;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "member/joinForm";
    }

    @GetMapping("/idcheck")
    public String idcheck(@RequestParam("userid") String userid, Model model) {
        MemberDto mdto = ms.getMember(userid);
        if(mdto==null) model.addAttribute("result",1);
        else model.addAttribute("result",-1);

        model.addAttribute("userid",userid);
        return "member/idcheck";
    }

    @PostMapping("/join")
    public String join(
            @ModelAttribute("dto") @Valid MemberDto memberdto, BindingResult result,
            @RequestParam(value="reid",required = false) String reid,
            @RequestParam(value="pwd_check",required = false) String pwd_check,
            Model model) {
        String url="member/joinForm";
        model.addAttribute("reid",reid);
        if(result.getFieldError("userid")!=null) model.addAttribute("msg", result.getFieldError("userid").getDefaultMessage());
        else if(result.getFieldError("pwd")!=null) model.addAttribute("msg", result.getFieldError("pwd").getDefaultMessage());
        else if(result.getFieldError("name")!=null) model.addAttribute("msg", result.getFieldError("name").getDefaultMessage());
        else if(result.getFieldError("email")!=null) model.addAttribute("msg", result.getFieldError("email").getDefaultMessage());
        else if(result.getFieldError("phone")!=null) model.addAttribute("msg", result.getFieldError("phone").getDefaultMessage());
        else if(reid==null || !memberdto.getUserid().equals(reid))
            model.addAttribute("msg","아이디 중복 검사 진행");
        else if(pwd_check==null || !memberdto.getPwd().equals(pwd_check))
            model.addAttribute("msg","비밀번호 확인이 일치하지 않음");
        else{
            ms.insertMember(memberdto);
            model.addAttribute("msg","회원가입 완료. 로그인해");
            url="member/loginForm";
        }
        return url;
    }

    @GetMapping("/updateMemberForm")
    String updateMemberForm(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        MemberDto mdto = (MemberDto)session.getAttribute("loginUser");
        model.addAttribute("dto",mdto);
        return "member/updateMember";
    }

    @PostMapping("/updateMember")
    public String updateMember(
            @ModelAttribute("dto") @Valid MemberDto memberdto, BindingResult result,
            @RequestParam(value="pwd_check",required = false) String pwd_check,
            HttpServletRequest request,Model model) {
        String url="member/updateMember";
        if(result.getFieldError("pwd")!=null) model.addAttribute("msg", result.getFieldError("pwd").getDefaultMessage());
        else if(result.getFieldError("name")!=null) model.addAttribute("msg", result.getFieldError("name").getDefaultMessage());
        else if(result.getFieldError("email")!=null) model.addAttribute("msg", result.getFieldError("email").getDefaultMessage());
        else if(result.getFieldError("phone")!=null) model.addAttribute("msg", result.getFieldError("phone").getDefaultMessage());
        else if(pwd_check==null || !memberdto.getPwd().equals(pwd_check))
            model.addAttribute("msg","비밀번호 확인이 일치하지 않음");
        else{
            ms.updateMember(memberdto);
            HttpSession session = request.getSession();
            session.setAttribute("loginUser",memberdto);
            url="redirect:/main";
        }
        return url;
    }

    @GetMapping("/deleteMember")
    public String deleteMember(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        MemberDto mdto = (MemberDto)session.getAttribute("loginUser");
        ms.deleteMember(mdto.getUserid());
        model.addAttribute("msg","회원탈퇴 완료");
        return "member/loginForm";
    }

    @GetMapping("/kakaostart")
    public @ResponseBody String kakaostart() {
        String a = "<script type='text/javascript'>" +
                "location.href='https://kauth.kakao.com/oauth/authorize?" +
                "client_id=43e8ee9e70d6ad0cb4772ef2cf0f58e7" +
                "&redirect_uri=http://localhost:8070/kakaoLogin" +
                "&response_type=code'" +
                "</script>";
        return a;
    }

    @GetMapping("/kakaoLogin")
    public String loginkakao(@RequestParam("code") String code, HttpServletRequest request) throws IOException {
        System.out.println("1차 수신 코드 : " + code); // 앱키를 이용한 최초 인증 수신 코드

        // 개인정보 요청을 위한 1차 토큰을 요청합니다
        // 요청할 url과 전달인수 설정
        String endpoint="https://kauth.kakao.com/oauth/token";
        URL url=new URL(endpoint);

        String bodyData="grant_type=authorization_code";
        bodyData +="&client_id=43e8ee9e70d6ad0cb4772ef2cf0f58e7";
        bodyData +="&redirect_uri=http://localhost:8070/kakaoLogin";
        bodyData +="&code="+code;

        // url 객체에 bodydata 를 더해서 요청합니다

        //Stream 연결  // import - java.net.HttpURLConnection;
        HttpURLConnection conn=(HttpURLConnection)url.openConnection();
        //http header 값 넣기(요청 내용에 헤더 추가)
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        conn.setDoOutput(true); // 송신설정
        BufferedWriter bw=new BufferedWriter(
                new OutputStreamWriter(conn.getOutputStream(),"UTF-8")
        );
        bw.write(bodyData);
        bw.flush();  // 실제송신 시점

        // 요청에 의한 수신 객체 생성
        BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), "UTF-8")
        );
        String input="";
        StringBuilder sb=new StringBuilder();  // 조각난 String 을 조립하기위한 객체
        while((input=br.readLine())!=null){ // 응답 수신
            sb.append(input); // 수신 내용의 누적
            System.out.println(input); // 수신 내용을 그때 그때 출력
        }

        //사용자 정보를 요청하기 위한 2차 수신 내용(토큰)
        //{"access_token":"jZ4XsNqxWK0_qPDp7k0mqq7XQw5CxfiCAAAAAQorDKYAAAGTTD1m9MwhKCpFsUJR","token_type":"bearer","refresh_token":"H2lR1kz6rkuJFFQQVJ1jVl3zOk-TNDVhAAAAAgorDKYAAAGTTD1m8cwhKCpFsUJR","expires_in":21599,"scope":"profile_nickname","refresh_token_expires_in":5183999}

        Gson gson=new Gson();
        // sb -> oAuthToken 복사
        OAuthToken oAuthToken = gson.fromJson(sb.toString(),OAuthToken.class);
        System.out.println(oAuthToken.getAccess_token());
        System.out.println(oAuthToken.getRefresh_token());
        System.out.println(oAuthToken.getScope());
        System.out.println(oAuthToken.getExpires_in());


        endpoint="https://kapi.kakao.com/v2/user/me";
        url =new URL(endpoint);
        conn=(HttpsURLConnection)url.openConnection();
        conn.setRequestProperty("Authorization", "Bearer" + oAuthToken.getAccess_token());
        conn.setDoOutput(true);
        br=new BufferedReader(
                new InputStreamReader(conn.getInputStream(),"UTF-8")
        );
        input="";
        sb=new StringBuilder();
        while((input=br.readLine())!=null) {
            sb.append(input);
            System.out.println(input);
        }

        gson=new Gson();
        KakaoProfile kakaoProfile = gson.fromJson(sb.toString(),KakaoProfile.class);

        System.out.println("id : " + kakaoProfile.getId());

        KakaoProfile.KakaoAccount ac = kakaoProfile.getAccount();
        System.out.println("email : " + ac.getEmail());

        KakaoProfile.KakaoAccount.Profile pf = ac.getProfile();
        System.out.println("nickname : " + pf.getNickname());

        // 회원 가입 여부 조회
        MemberDto mdto = ms.getMember(kakaoProfile.getId());
        // 조회 결과 회원이 존재하면 세션에 mdto 를 저장하고 main 으로 이동
        // 회원이 없으면 회원 가입하고 오세요
        if(mdto==null){
            mdto=new MemberDto();
            mdto.setUserid(kakaoProfile.getId());
            mdto.setName(pf.getNickname());
            mdto.setEmail(ac.getEmail());
            mdto.setProvider("kakao");
        }

        HttpSession session = request.getSession();
        session.setAttribute("loginUser",mdto);
        return "redirect:/main";


    }

}
