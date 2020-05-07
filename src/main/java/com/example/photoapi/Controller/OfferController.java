package com.example.photoapi.Controller;

import com.example.photoapi.DTO.OfferDTO;
import com.example.photoapi.InterFace.OfferInterface;
import com.example.photoapi.Model.Offer;
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
@RequestMapping(value = "/OfferApi")
public class OfferController<offerDTO> {

    @Autowired private OfferInterface offerInterface;

    @Autowired private UserController userController;

//    @RequestMapping(value = "/getAllUserOffers/{email}", method = RequestMethod.GET)
//    public ResponseEntity<List<Offer>> getAllUserOffers(@PathVariable(value = "email")String email){
//        try {
//            List<Offer> offers =  offerInterface.getAllUserOffers(userController.getOneUser(email).getBody());
//            if(offers.isEmpty()){
//                return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//            return new ResponseEntity<>(offers, HttpStatus.OK);
//        }catch (Exception x){
//            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
//        }
//    }
    /**********************************************************************************/

    OfferDTO offerDTOclass = new OfferDTO();

    @RequestMapping(value = "/getAllUserOffers/{email}", method = RequestMethod.GET)
    public ResponseEntity<List<OfferDTO>> getAllUserOffersSlice(@PathVariable(value = "email")String email){

        ModelMapper modelMapper = new ModelMapper();
        List<OfferDTO> offerDTOList = new ArrayList<>();

        try {
            List<Offer> offers =  offerInterface.getAllUserOffers(userController.getOneUser(email).getBody());
            if(offers.isEmpty()){
                return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            else {
                for (Offer o:offers) {
                    modelMapper.map(o,offerDTOclass);
                    offerDTOList.add(offerDTOclass);
                            
                }
            }
            return new ResponseEntity(offerDTOList, HttpStatus.OK);
        }catch (Exception x){
            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }
    }
}
