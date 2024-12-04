package com.himedia.sp13.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReplyDto {
    private int replynum;
    private int boardnum;
    private String userid;
    private Timestamp writedate;

    @NotEmpty(message = "내용을 입력해")
    @NotNull(message = "내용을 입력해")
    private String content;
}
