package com.ecommers.service;

import com.ecommers.model.Colour;
import com.ecommers.repository.ColourRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ColourServiceTest {
    @InjectMocks
    private ColourService colourService;
    @Mock
    private ColourRepository colourRepository;

    @Test
    void testSaveNewColour_whenColourNotExits() {
        String colourName="Red";
        Mockito.when(colourRepository.findByColourName(colourName)).thenReturn(null);
        String result = colourService.saveOrUpdateColour(colourName);
        assertEquals("colour is saved",result);

    }

    @Test
    void testUpdateColour_whenColourExits() {
        String colourName="Blue";
        Colour existingColour = new Colour();
        existingColour.setColourName("OldBlue");
        Mockito.when(colourRepository.findByColourName(colourName)).thenReturn(existingColour);

        String result = colourService.saveOrUpdateColour(colourName);
        assertEquals("colour  updated", result);


    }
    @Test
    void getAllColours(){
        Colour colour1=new Colour();
        colour1.setColourId(1);
        colour1.setColourName("red");

        Colour colour2=new Colour();
        colour1.setColourId(1);
        colour1.setColourName("red");

        Colour colour3=new Colour();
        colour1.setColourId(1);
        colour1.setColourName("red");

        List<Colour> colourList = Arrays.asList(colour1, colour2, colour3);

        Mockito.when(colourRepository.findAll()).thenReturn(colourList);
        List<Colour> allColour = colourService.getAllColour();
        assertEquals(3,allColour.size());

    }

    @Test
    void getAllColourName(){
        List<String> colourNames = Arrays.asList("red", "blue", "green");

        Mockito.when(colourRepository.findByColourNames()).thenReturn(colourNames);

        List<String> result = colourService.getColourNames();

        assertEquals(3, result.size());
        assertTrue(result.contains("red"));
        assertTrue(result.contains("blue"));
        assertTrue(result.contains("green"));

    }
}