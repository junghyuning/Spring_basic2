package com.fastcampus.ch4.domain;

import lombok.Data;

import java.util.Date;
import java.util.Objects;
@Data
public class BoardDto {
    private Integer bno;
    private String title;
    private String content;
    private String writer;
    private int viewCnt;
    private int commentCnt;
    private Date regDate;
    private Date upDate;

    public BoardDto() {
    }

    public BoardDto(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardDto boardDto = (BoardDto) o;
        return Objects.equals(bno, boardDto.bno) && Objects.equals(title, boardDto.title) && Objects.equals(content, boardDto.content) && Objects.equals(writer, boardDto.writer);
    }


}
