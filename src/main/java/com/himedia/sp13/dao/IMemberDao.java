package com.himedia.sp13.dao;

import com.himedia.sp13.dto.MemberDto;
import jakarta.validation.Valid;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IMemberDao {

    MemberDto getMember(String userid);

    void insert(MemberDto memberdto);

    void update(MemberDto memberdto);

    void delete(String userid);
}
