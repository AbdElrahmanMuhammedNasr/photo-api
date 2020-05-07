package com.example.photoapi.DTO;

import lombok.Data;

import java.sql.Date;

@Data
public class FreeTimeDTO {
    private Date day;
    private String fromTime;
    private String toTime;
    private String userName;
}
