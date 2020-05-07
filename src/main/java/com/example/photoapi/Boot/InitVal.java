package com.example.photoapi.Boot;

import com.example.photoapi.Model.Offer;
import com.example.photoapi.Model.User;
import com.example.photoapi.Model.WorkPalces;
import com.example.photoapi.Repo.OfferRepo;
import com.example.photoapi.Repo.UserRepo;
import com.example.photoapi.Repo.WorkPlaceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class InitVal implements CommandLineRunner {
    @Autowired private UserRepo userRepo;
    @Autowired private WorkPlaceRepo workPlaceRepo;
    @Autowired private OfferRepo offerRepo;

    @Override
    public void run(String... args) throws Exception {





        List<WorkPalces> list = new ArrayList<>();
        List<Offer> list1 = new ArrayList<>();


        User user = new User();

        user.setEmail("www.abdo@abdo.com");
        user.setUserName("abbdo");
        user.setJob("ph");
        user.setPrice(122);
        user.setCity("tanta");
        user.setWorkPalces(list);
        user.setOffers(list1);

        userRepo.save(user);



        WorkPalces workPalces = new WorkPalces();
        workPalces.setCity("Tanta");
        workPalces.setUser(user);
        workPlaceRepo.save(workPalces);

        WorkPalces workPalces1 = new WorkPalces();
        workPalces1.setCity("Alex");
        workPalces1.setUser(user);
        workPlaceRepo.save(workPalces1);


        WorkPalces workPalces2 = new WorkPalces();
        workPalces2.setCity("Cairo");
        workPalces2.setUser(user);
        workPlaceRepo.save(workPalces2);


        list.add(workPalces);
        list.add(workPalces2);
        list.add(workPalces1);


        Offer offer = new Offer();
        offer.setOfferName("discount");
        offer.setDetails("i will dis about 10% ");
        offer.setUser(user);
        offerRepo.save(offer);

        Offer offer2 = new Offer();
        offer2.setOfferName("discount");
        offer2.setDetails("i will dis about 10% ");
        offer2.setUser(user);
        offerRepo.save(offer2);

        Offer offer3 = new Offer();
        offer3.setOfferName("discount");
        offer3.setDetails("i will dis about 10% ");
        offer3.setUser(user);
        offerRepo.save(offer3);

        list1.add(offer);
        list1.add(offer2);
        list1.add(offer3);


    }
}
