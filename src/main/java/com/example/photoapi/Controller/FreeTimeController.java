package com.example.photoapi.Controller;

import com.example.photoapi.DTO.FreeTimeDTO;
import com.example.photoapi.InterFace.FreeTimeInterface;
import com.example.photoapi.Model.FreeTime;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/FreeTimeApi")
public class FreeTimeController {

    @Autowired private FreeTimeInterface freeTimeInterface;

    FreeTimeDTO freeTimeDTO = new FreeTimeDTO();
    ModelMapper modelMapper = new ModelMapper();

    @RequestMapping(value = "/getAllFreeTime/{date}", method = RequestMethod.GET)
    @ApiOperation(value = "get all Free Time",notes = "this api to get all User Free time ")
    public ResponseEntity<List<FreeTimeDTO>> getAllFreeTime(@PathVariable(value = "date")Date date){
        try{
            List<FreeTimeDTO> freeTimeDTOList = new ArrayList<>();
            List<FreeTime> freeTimeList = freeTimeInterface.getAllFreeTime(date);
            if(freeTimeList.isEmpty()){
                return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else {
                for (FreeTime f:freeTimeList) {
                    modelMapper.map(f, freeTimeDTO);
                    freeTimeDTOList.add(freeTimeDTO);
                }
                return new ResponseEntity<>(freeTimeDTOList, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED) ;
        }
    }
}
