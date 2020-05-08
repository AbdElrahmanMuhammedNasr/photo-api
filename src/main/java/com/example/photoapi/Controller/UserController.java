package com.example.photoapi.Controller;


import com.example.photoapi.InterFace.UserInterface;
import com.example.photoapi.Model.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/UserApi")
public class UserController {

    @Autowired private UserInterface userInter;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "get all user", notes = "this api to get all user", response = User.class)
    public ResponseEntity< List<User>> getAllUsers(){
        try {
            List<User> users =  userInter.getAllUsers();
            if(users.isEmpty()){
                return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(users, HttpStatus.FOUND);
        }catch (Exception x){
            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }
    }
    /*************************************************************************/

    @RequestMapping(value = "/getList/{username}", method = RequestMethod.GET)
    @ApiOperation(value = "get all user same Username", notes = "this api to get all user with same username", response = User.class)
    public ResponseEntity< List<User>> getListWithSameUsername(@PathVariable(value = "username")String username){
        try {
            List<User> users =  userInter.getListOfUserWithSameUserName(username);
            if(users.isEmpty()){
                return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(users, HttpStatus.FOUND);
        }catch (Exception x){
            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }
    }
    /*************************************************************************/

    @RequestMapping(value = "/getOneUser/{email}", method = RequestMethod.GET)
    @ApiOperation(value = "get one user using email", notes = "this api to get one user", response = User.class)
    public ResponseEntity<User> getOneUser(@PathVariable(value = "email")String email){
        try {
            User user =  userInter.getUserByEmail(email);
            if(user ==null){
                return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        }catch (Exception x){
            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }
    }

    /*********************************************************Post*******************************************************************/
    @RequestMapping(value = "/addNewUser", method = RequestMethod.POST)
    @ApiOperation(value = "add new User", notes = "this  api  to create new user using some data")
    public ResponseEntity<Void> addNewUser(@RequestBody User user){
        try {
            userInter.addNewUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception x){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }

    /*********************************************************Update*******************************************************************/
    @RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
    @ApiOperation(value = " update User", notes = "this  api  to update user using ID")
    public ResponseEntity<Void> updateUser(@RequestBody User user){
        try {
            userInter.updateUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception x){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }

    /*********************************************************delete*******************************************************************/
    @RequestMapping(value = "/deleteUser/{email}", method = RequestMethod.DELETE)
    @ApiOperation(value = " delete User", notes = "this  api  to delete user using Email")
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "email") String email){
        try {
            userInter.deleteUserUsingEmail(email);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception x){
            System.out.println(x.getMessage());
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }
}







