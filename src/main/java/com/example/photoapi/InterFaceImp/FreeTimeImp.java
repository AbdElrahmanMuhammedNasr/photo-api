package com.example.photoapi.InterFaceImp;

import com.example.photoapi.InterFace.FreeTimeInterface;
import com.example.photoapi.Model.FreeTime;
import com.example.photoapi.Repo.FreeTimeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class FreeTimeImp implements FreeTimeInterface {
    @Autowired FreeTimeRepo freeTimeRepo;

    @Override
    public List<FreeTime> getAllFreeTime(Date date) {
        return freeTimeRepo.findAllByDay(date);
    }
}
