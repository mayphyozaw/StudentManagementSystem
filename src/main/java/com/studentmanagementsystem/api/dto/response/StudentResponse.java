package com.studentmanagementsystem.api.dto.response;

import com.studentmanagementsystem.api.dto.request.StudentRequestDto;
import lombok.Data;

import java.util.List;

@Data
public class StudentResponse {

    private List<StudentRequestDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
