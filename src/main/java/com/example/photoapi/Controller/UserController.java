package com.example.photoapi.Controller;


import com.example.photoapi.InterFace.UserInterface;
import com.example.photoapi.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/Userapi")
public class UserController {

    @Autowired private UserInterface userInter;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity< List<User>> getAllUsers(){
        try {
            List<User> users =  userInter.getAllUsers();
            if(users.isEmpty()){
                return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        }catch (Exception x){
            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }
    }
    /*************************************************************************/

    @RequestMapping(value = "/getList/{userName}", method = RequestMethod.GET)
    public ResponseEntity< List<User>> getListWithSameUsername(@PathVariable(value = "userName")String username){
        try {
            List<User> users =  userInter.getListOfUserWithSameUserName(username);
            if(users.isEmpty()){
                return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        }catch (Exception x){
            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }
    }
    /*************************************************************************/

    @RequestMapping(value = "/getOneUser/{email}", method = RequestMethod.GET)
    public ResponseEntity<User> getOneUser(@PathVariable(value = "email")String email){
        try {
            User user =  userInter.getUserByEmail(email);
            if(user ==null){
                return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (Exception x){
            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }
    }
}
