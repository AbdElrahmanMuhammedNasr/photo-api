package com.example.photoapi.Controller;

import com.example.photoapi.DTO.OfferDTO;
import com.example.photoapi.DTO.WorkPalcesDTO;
import com.example.photoapi.InterFace.WorkPlaceInterface;
import com.example.photoapi.Model.Offer;
import com.example.photoapi.Model.WorkPalces;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/WorkApi")
public class WorkController {

    @Autowired private WorkPlaceInterface workPlaceInterface;
    @Autowired private UserController userController;

    private WorkPalcesDTO workPalcesDTOClass = new WorkPalcesDTO();
    private ModelMapper modelMapper = new ModelMapper();


    @RequestMapping(value = "/getAllUserWork/{email}", method = RequestMethod.GET)
    public ResponseEntity<List<WorkPalcesDTO>> getAllWorkplace(@PathVariable(value = "email") String email) {
         List<WorkPalcesDTO> workPalcesDTOList = new ArrayList<>();
        try {
            List<WorkPalces> workPalces =  workPlaceInterface.getAllUserWorkPalce(userController.getOneUser(email).getBody());
            if(workPalces.isEmpty()){
                return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else {
                for (WorkPalces work:workPalces) {
                    modelMapper.map(work, workPalcesDTOClass);
                    workPalcesDTOList.add(workPalcesDTOClass);
                }
            }
            return new ResponseEntity<>(workPalcesDTOList, HttpStatus.OK);
        }catch (Exception x){
            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }



    }
}