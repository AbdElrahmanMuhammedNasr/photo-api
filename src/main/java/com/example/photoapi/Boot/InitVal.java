package com.example.photoapi.Boot;

import com.example.photoapi.Model.FreeTime;
import com.example.photoapi.Model.Offer;
import com.example.photoapi.Model.User;
import com.example.photoapi.Model.WorkPalces;
import com.example.photoapi.Repo.FreeTimeRepo;
import com.example.photoapi.Repo.OfferRepo;
import com.example.photoapi.Repo.UserRepo;
import com.example.photoapi.Repo.WorkPlaceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Component
public class InitVal implements CommandLineRunner {
    @Autowired private UserRepo userRepo;
    @Autowired private WorkPlaceRepo workPlaceRepo;
    @Autowired private OfferRepo offerRepo;
    @Autowired private FreeTimeRepo freeTimeRepo;


    @Override
    public void run(String... args) throws Exception {

        List<WorkPalces> list = new ArrayList<>();
        List<Offer> list1 = new ArrayList<>();
        List<FreeTime> list2 = new ArrayList<>();


        User user = new User();

        user.setEmail("www.abdo@abdo.com");
        user.setUserName("abbdo");
        user.setJob("ph");
        user.setPrice(122);
        user.setCity("tanta");
        user.setPhoneNumber("01205338791");
        user.setWorkPalces(list);
        user.setOffers(list1);
        user.setFreeTimes(list2);

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

        Calendar c = Calendar.getInstance();
        c.set(2020,10,10);

        Offer offer = new Offer();
        offer.setOfferName("discount");
        offer.setDetails("i will dis about 10% ");
        offer.setEndOffer( c.getTime() );
        offer.setUser(user);
        offerRepo.save(offer);

        Offer offer2 = new Offer();
        offer2.setOfferName("discount");
        offer2.setDetails("i will dis about 10% ");
        offer2.setEndOffer( c.getTime());
        offer2.setUser(user);
        offerRepo.save(offer2);

        Offer offer3 = new Offer();
        offer3.setOfferName("discount");
        offer3.setDetails("i will dis about 10% ");
        offer3.setEndOffer( c.getTime());
        offer3.setUser(user);
        offerRepo.save(offer3);

        list1.add(offer);
        list1.add(offer2);
        list1.add(offer3);

        java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());

        FreeTime freeTime = new FreeTime();
        freeTime.setDay(sqlDate);
        freeTime.setFromTime("5 AM");
        freeTime.setToTime("6 AM");
        freeTime.setUser(user);
        freeTimeRepo.save(freeTime);

        FreeTime freeTime1 = new FreeTime();
        freeTime1.setDay(sqlDate);
        freeTime1.setFromTime("8 AM");
        freeTime1.setToTime("10 AM");
        freeTime1.setUser(user);
        freeTimeRepo.save(freeTime1);

        list2.add(freeTime);
        list2.add(freeTime1);


    }
}
