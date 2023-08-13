package com.fastcampus.ch4.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchCondition {
    private Integer page = 1;
    private Integer pageSize = 10;
    private String keyword = "";
    private String option = "";
    public SearchCondition() {
    }


}
