package com.nish.PdfDynamicCreation.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nish.PdfDynamicCreation.Entity.City;
import com.nish.PdfDynamicCreation.Repository.ICityRepository;


@Service
public class CityService implements ICityService {

    @Autowired
    private ICityRepository repository;

    @Override
    public List<City> findAll() {

        return (List<City>) repository.findAll();
    }
}