package com.studentmanagementsystem.api.dto.response;

import com.studentmanagementsystem.api.dto.request.CourseRequestDto;
import lombok.Data;

import java.util.List;

@Data
public class CourseResponse {

    private List<CourseRequestDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
