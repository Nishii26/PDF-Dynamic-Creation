package com.nish.PdfDynamicCreation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nish.PdfDynamicCreation.Entity.City;

@Repository
public interface ICityRepository extends JpaRepository<City,Long> {
}
