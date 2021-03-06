package com.example.photoapi.InterFaceImp;

import com.example.photoapi.InterFace.WorkPlaceInterface;
import com.example.photoapi.Model.User;
import com.example.photoapi.Model.WorkPalces;
import com.example.photoapi.Repo.WorkPlaceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkPlaceImp implements WorkPlaceInterface {

    @Autowired private WorkPlaceRepo workPlaceRepo;

    @Override
    public List<WorkPalces> getAllUserWorkPalce(User user) {
        return workPlaceRepo.findByUser(user);
    }

    @Override
    public List<WorkPalces> getAllWorkPlace(String city) {
        return workPlaceRepo.findAllByCity(city);
    }

    @Override
    public WorkPalces getOneWorkPlace(Long id) {
        return workPlaceRepo.findById(id).get();
    }

    @Override
    public void addNewWorkPlace(WorkPalces workPalces) {
        workPlaceRepo.save(workPalces);
    }

    @Override
    public void updateWorkPlace(WorkPalces workPalces) {
        workPlaceRepo.save(workPalces);
    }

    @Override
    public void deleteWorkPlace(Long id) {
        workPlaceRepo.deleteById(id);
    }


}
