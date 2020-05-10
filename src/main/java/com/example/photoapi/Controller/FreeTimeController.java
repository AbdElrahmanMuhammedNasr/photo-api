package com.example.photoapi.Controller;

import com.example.photoapi.DTO.FreeTimeDTO;
import com.example.photoapi.InterFace.FreeTimeInterface;
import com.example.photoapi.Model.FreeTime;
import com.example.photoapi.Model.User;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/FreeTimeApi")
public class FreeTimeController {

    @Autowired private FreeTimeInterface freeTimeInterface;
    @Autowired private UserController userController;


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
    /************************************************************************************************/

    @RequestMapping(value = "/getOneFreeTime/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "get One  Free Time",notes = "this api to get One User Free time  using id")
    public ResponseEntity<FreeTime>getOneFreeTime(@PathVariable(value = "id") Long id){
        try{
            FreeTime freeTime = freeTimeInterface.getOneFreeTime(id);
            if(freeTime !=null){
                return  new ResponseEntity<>(freeTime,HttpStatus.FOUND);
            }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED) ;
        }
    }

    /****************************************Post****************************************************/
    @RequestMapping(value = "/addNewFreeTime/{email}", method = RequestMethod.POST)
    @ApiOperation(value = "add new Free Time",notes = "this api to add new  User Free time ")
    public ResponseEntity<Void>addNewFreeTime(@PathVariable(value = "email")String email, @RequestBody FreeTime freeTime){
        try{
            ResponseEntity userResponseEntity = userController.getOneUser(email);
            if(userResponseEntity.getStatusCodeValue() == 302){
                User user =  (User) userResponseEntity.getBody();
                freeTime.setUser(user);
                freeTimeInterface.savenewFreeTime(freeTime);
                return new ResponseEntity<>(HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED) ;
        }
    }
    /****************************************Update****************************************************/
    @RequestMapping(value = "/updateFreeTime/{email}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update Free Time",notes = "this api to  update  User Free time  using freetime id")
    public ResponseEntity<Void>updateFreeTime(@PathVariable(value = "email")String email, @RequestBody FreeTime freeTime){
        try{
            ResponseEntity userResponseEntity = userController.getOneUser(email);
            if(userResponseEntity.getStatusCodeValue() == 302){
                User user =  (User) userResponseEntity.getBody();
                FreeTime freeTime1 = freeTimeInterface.getOneFreeTime(freeTime.getId());
                if(user.getEmail().equals(freeTime1.getUser().getEmail())){
                    freeTime.setUser(user);
                    freeTimeInterface.updateFreeTime(freeTime);
                }else {
                    return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
                }
                return new ResponseEntity<>(HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED) ;
        }
    }
    /****************************************Delete****************************************************/
    @RequestMapping(value = "/deleteFreeTime/{email}/{freeTimeId}", method = RequestMethod.DELETE)
    @ApiOperation(value = "delete Free Time",notes = "this api to  delete  User Free time  using freetime")
    public ResponseEntity<Void>deleteFreeTime(@PathVariable(value = "email")String email, @PathVariable(value = "freeTimeId")Long id){
        try{
            ResponseEntity userResponseEntity = userController.getOneUser(email);
            if(userResponseEntity.getStatusCodeValue() == 302){
                User user =  (User) userResponseEntity.getBody();
                FreeTime freeTime = freeTimeInterface.getOneFreeTime(id);
                if(user.getEmail().equals(freeTime.getUser().getEmail())){
                    freeTime.setUser(user);
                    freeTimeInterface.deleteFreeTime(freeTime);
                    return new ResponseEntity<>(HttpStatus.OK);
                }
                else {
                    return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
                }
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }



}
