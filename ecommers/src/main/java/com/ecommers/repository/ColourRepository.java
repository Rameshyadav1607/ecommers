package com.ecommers.repository;


import com.ecommers.model.Colour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ColourRepository extends JpaRepository<Colour, Integer> {
    Colour findByColourName(String colourName);
    @Query("select c.colourName from Colour c")
    List<String> findByColourNames();
}