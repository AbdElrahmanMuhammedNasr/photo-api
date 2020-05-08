package com.example.photoapi.Controller;

import com.example.photoapi.DTO.OfferDTO;
import com.example.photoapi.InterFace.OfferInterface;
import com.example.photoapi.Model.Offer;
import com.example.photoapi.Model.User;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.UniqueElements;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(value = "/OfferApi")
public class OfferController {

    @Autowired private OfferInterface offerInterface;

    @Autowired private UserController userController;

    ModelMapper modelMapper = new ModelMapper();

    //    @RequestMapping(value = "/getAllUserOffers/{email}", method = RequestMethod.GET)
    //    public ResponseEntity<List<Offer>> getAllUserOffers(@PathVariable(value = "email")String email){
    //        try {
    //            List<Offer> offers =  new ArrayList<>();
    //            offers =offerInterface.getAllUserOffers(userController.getOneUser(email).getBody());
    //            if(offers.isEmpty()){
    //                return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
    //            }
    //            return new ResponseEntity<>(offers, HttpStatus.OK);
    //        }catch (Exception x){
    //            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
    //        }
    //    }
    /**********************************************************************************/

    @RequestMapping(value = "/getAllUserOffers/{email}", method = RequestMethod.GET)
    @ApiOperation(value = "Find  offers ", notes = "this api used to find All user offers" ,response = OfferDTO.class)
    public ResponseEntity<List<OfferDTO>> getAllUserOffersSlice(@PathVariable(value = "email")String email){
        List<OfferDTO> offerDTOList = new ArrayList<>();
        try {
            List<Offer> offers = offerInterface.getAllUserOffers(userController.getOneUser(email).getBody());
            if(offers.isEmpty()){
                return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                for (Offer offer: offers) {
                    OfferDTO offerDTOclass = new OfferDTO();
                     modelMapper.map(offer, offerDTOclass);
                     offerDTOList.add(0,offerDTOclass);
                }
            }
            return new ResponseEntity(offerDTOList, HttpStatus.FOUND);
        }catch (Exception x){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

//    @RequestMapping(value = "/getAllUserOffers/{id}", method = RequestMethod.GET)
//    @ApiOperation(value = "Find  offer ", notes = "this api used to find user offer using Id" ,response = OfferDTO.class)
//    public ResponseEntity<Offer> getOenUserOffer(@PathVariable(value = "id")Long id){
//
//        try {
//            Offer offer = offerInterface.getOneOfferUsingId(id);
//            if(offer != null){
//                return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//            return new ResponseEntity(offer, HttpStatus.OK);
//        }catch (Exception x){
//            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
//        }
//    }


    /***************************************post******************************************/
    @RequestMapping(value = "/addNewOffer/{email}", method = RequestMethod.POST)
    @ApiOperation(value = "add offer", notes = "this api used to add offers" ,response = void.class)
    public ResponseEntity<Void> addNewOffer(@PathVariable(value = "email") String email, @RequestBody Offer offer){
            try {
                ResponseEntity userResponseEntity = userController.getOneUser(email);
                if(userResponseEntity.getStatusCodeValue() == 302){
                    User user = (User) userResponseEntity.getBody();
                    offer.setUser(user);
                    offerInterface.addNewOffer(offer);
                    return new ResponseEntity<>(HttpStatus.ACCEPTED);
                }
                else {
                    return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
                }
            }catch (Exception x){
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }
    }

    /***************************************Update******************************************/
    @RequestMapping(value = "/updateOffer/{email}", method = RequestMethod.PUT)
    @ApiOperation(value = "update offer", notes = "this api used to update exist offers using ID" ,response = void.class)
    public ResponseEntity<Void> updateOffer(@PathVariable(value = "email") String email, @RequestBody Offer offer){
        try {
            ResponseEntity userResponseEntity = userController.getOneUser(email);
            if(userResponseEntity.getStatusCodeValue() == 302){
                User user = (User) userResponseEntity.getBody();
                Offer offer1 = offerInterface.getOneOfferUsingId(offer.getId());
                if(offer1.getUser().getEmail().equals(user.getEmail())){
                    offer.setUser(user);
                    offerInterface.updateOffer(offer);
                    return new ResponseEntity<>(HttpStatus.OK);
                }else {
                    return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
                }
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        }catch (Exception x){
            System.out.println(x.getMessage());
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /***************************************Delete******************************************/
    @RequestMapping(value = "/deleteOffer/{email}/{offerId}", method = RequestMethod.DELETE)
    @ApiOperation(value = "delete offer", notes = "this api used to delete exist offers using ID" ,response = void.class)
    public ResponseEntity<Void> deleteOffer(@PathVariable(value = "email") String email, @PathVariable(value = "offerId") Long offerId){
        try {
            ResponseEntity userResponseEntity = userController.getOneUser(email);
            if(userResponseEntity.getStatusCodeValue() == 302){
                User user = (User) userResponseEntity.getBody();
                Offer offer = offerInterface.getOneOfferUsingId(offerId);
                if(offer.getUser().getEmail().equals(user.getEmail())){
                    offerInterface.deleteOffer(offer);
                    return new ResponseEntity<>(HttpStatus.OK);
                }else {
                    return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
                }
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        }catch (Exception x){
            System.out.println(x.getMessage());
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}
