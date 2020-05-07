package com.example.photoapi.InterFace;

import com.example.photoapi.Model.User;
import com.example.photoapi.Model.WorkPalces;

import java.util.List;

public interface WorkPlaceInterface {
    List<WorkPalces> getAllUserWorkPalce(User user);
    List<WorkPalces> getAllWorkPlace(String city);
}
