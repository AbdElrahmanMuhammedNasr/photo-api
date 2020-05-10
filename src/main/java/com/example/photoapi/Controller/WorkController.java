package com.example.photoapi.Controller;

import com.example.photoapi.DTO.WorkPalcesDTO;
import com.example.photoapi.InterFace.WorkPlaceInterface;
import com.example.photoapi.Model.FreeTime;
import com.example.photoapi.Model.User;
import com.example.photoapi.Model.WorkPalces;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/WorkApi")
public class WorkController {

    @Autowired private WorkPlaceInterface workPlaceInterface;
    @Autowired private UserController userController;
    private ModelMapper modelMapper = new ModelMapper();

//    @RequestMapping(value = "/getAllUserWork/{email}", method = RequestMethod.GET)
//    public ResponseEntity<List<WorkPalces>> getAllUserWorkplace(@PathVariable(value = "email") String email) {
//        try {
//            List<WorkPalces> workPalces =  workPlaceInterface.getAllUserWorkPalce(userController.getOneUser(email).getBody());
//            if(workPalces.isEmpty()){
//                return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//
//            return new ResponseEntity<>(workPalces, HttpStatus.OK);
//        }catch (Exception x){
//            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
//        }
//
//
//
//    }
    /**************************************************************************************************************/


    @RequestMapping(value = "/getAllUserWorkPlace/{email}", method = RequestMethod.GET)
    @ApiOperation(value = "get all user  Free Time",notes = "this api to all  User Free time  ")
    public ResponseEntity<List<WorkPalcesDTO>> getAllUserWorkplace(@PathVariable(value = "email") String email) {
         List<WorkPalcesDTO> workPalcesDTOList = new ArrayList<>();
        try {
            List<WorkPalces> workPalces =  workPlaceInterface.getAllUserWorkPalce(userController.getOneUser(email).getBody());
            if(workPalces.isEmpty()){
                return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else {
                for (WorkPalces work:workPalces) {
                    WorkPalcesDTO workPalcesDTOClass = new WorkPalcesDTO();
                    modelMapper.map(work, workPalcesDTOClass);
                    workPalcesDTOList.add(workPalcesDTOClass);
                }
            }
            return new ResponseEntity<>(workPalcesDTOList, HttpStatus.OK);
        }catch (Exception x){
            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }

    }
    /**************************************************************************************************************/

    @RequestMapping(value = "/getAllWorkPlace/{city}", method = RequestMethod.GET)
    @ApiOperation(value = "get all  Free Time",notes = "this api to get all User and Free time  using city")
    public ResponseEntity<List<WorkPalcesDTO>> getAllWorkplace(@PathVariable(value = "city") String city) {
        List<WorkPalcesDTO> workPalcesDTOList = new ArrayList<>();
        try {
            List<WorkPalces> workPalces =  workPlaceInterface.getAllWorkPlace(city);
            if(workPalces.isEmpty()){
                return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else {
                for (WorkPalces work:workPalces) {
                    WorkPalcesDTO workPalcesDTOClass = new WorkPalcesDTO();
                    modelMapper.map(work, workPalcesDTOClass);
                    workPalcesDTOList.add(workPalcesDTOClass);
                }
            }
            return new ResponseEntity<>(workPalcesDTOList, HttpStatus.OK);
        }catch (Exception x){
            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }


    }

    /**********************************************Post****************************************************************/

    @RequestMapping(value = "/addNewWork/{email}", method = RequestMethod.POST)
    public ResponseEntity<Void> addNewWorkplace(@PathVariable(value = "email") String email, @RequestBody WorkPalces workPalces) {
        try {
                ResponseEntity userResponseEntity = userController.getOneUser(email);
                if (userResponseEntity.getStatusCodeValue() == 302){
                    User user = (User) userResponseEntity.getBody();
                    workPalces.setUser(user);
                    workPlaceInterface.addNewWorkPlace(workPalces);
                    return new ResponseEntity<>(HttpStatus.OK);
                }else {
                    return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
                }

        }catch (Exception x){
            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }

    }
    /****************************************Update****************************************************/

    @RequestMapping(value = "/updateWorkPlace/{email}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update work palce",notes = "this api to  update  User work place using id")
    public ResponseEntity<Void>updateWorkPlace(@PathVariable(value = "email")String email, @RequestBody WorkPalces workPalces){
        try{
            ResponseEntity userResponseEntity = userController.getOneUser(email);
            if(userResponseEntity.getStatusCodeValue() == 302){
                User user =  (User) userResponseEntity.getBody();
                WorkPalces workPalces1 = workPlaceInterface.getOneWorkPlace(workPalces.getId());
                if(user.getEmail().equals(workPalces1.getUser().getEmail())){
                    workPalces.setUser(user);
                    workPlaceInterface.updateWorkPlace(workPalces);
                    return new ResponseEntity<>(HttpStatus.OK);

                }else {
                    return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
                }
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED) ;
        }
    }
    /****************************************Delete****************************************************/

    @RequestMapping(value = "/deleteWorkPlace/{email}/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "delete work palce",notes = "this api to  delete  User work place using id")
    public ResponseEntity<Void>deleteWorkPlace(@PathVariable(value = "email")String email, @PathVariable(value = "id")Long id){
        try{
            ResponseEntity userResponseEntity = userController.getOneUser(email);
            if(userResponseEntity.getStatusCodeValue() == 302){
                User user =  (User) userResponseEntity.getBody();
                WorkPalces workPalces1 = workPlaceInterface.getOneWorkPlace(id);
                if(user.getEmail().equals(workPalces1.getUser().getEmail())){
                    workPlaceInterface.deleteWorkPlace(id);
                    return new ResponseEntity<>(HttpStatus.OK);

                }else {
                    return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
                }
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED) ;
        }
    }
}
