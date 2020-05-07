package com.example.photoapi.InterFace;

import com.example.photoapi.Model.FreeTime;

import java.sql.Date;
import java.util.List;

public interface FreeTimeInterface {
    List<FreeTime> getAllFreeTime(Date date);
}
