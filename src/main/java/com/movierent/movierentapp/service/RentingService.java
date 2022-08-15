package com.movierent.movierentapp.service;

import com.movierent.movierentapp.domen.Renting;
import com.movierent.movierentapp.repo.RentingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentingService {

    private RentingRepository rentingRepository;

    @Autowired
    public RentingService(RentingRepository rentingRepository) {
        this.rentingRepository = rentingRepository;
    }

    public long total() {return rentingRepository.count();}

    public Renting addRenting (Integer customerId, String rentingData, Double price) {
        return rentingRepository.save(new Renting(customerId, rentingData, price));
    }

}
