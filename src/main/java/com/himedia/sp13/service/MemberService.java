package com.himedia.sp13.service;

import com.himedia.sp13.dao.IMemberDao;
import com.himedia.sp13.dto.MemberDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    IMemberDao mdao;

    public MemberDto getMember(String userid) {
        MemberDto mdto=mdao.getMember(userid);
        return mdto;
        // return mdao.getMember(userid);
    }

    public void insertMember(MemberDto memberdto) {
        mdao.insert(memberdto);
    }

    public void updateMember(MemberDto memberdto) {
        mdao.update(memberdto);
    }

    public void deleteMember(String userid) {
        mdao.delete(userid);
    }
}
