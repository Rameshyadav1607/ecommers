package com.ecommers.service;

import com.ecommers.domain.BrandDomain;
import com.ecommers.model.Brand;
import com.ecommers.repository.BrandRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class BrandServiceTest {
    @InjectMocks
    private  BrandService brandService;
    @Mock
    private BrandRepository brandRepository;
    @Test
    void testUpdateExistingBrand(){

        BrandDomain brandDomain=new BrandDomain();
        brandDomain.setBrandName("Nike");
        brandDomain.setCreatedDate(LocalDateTime.now());

        Brand existingBrand=new Brand();
        existingBrand.setBrandName("Nike");
        existingBrand.setcreatedDate(LocalDateTime.now());

        when(brandRepository.findByBrandName("Nike")).thenReturn(existingBrand);

              brandService.saveOrUpdateBrand(brandDomain);

        verify(brandRepository).findByBrandName("Nike");
        verify(brandRepository).save(existingBrand);

    }

    @Test
    void testSaveOrUpdateBrand_new() {

        BrandDomain brandDomain = new BrandDomain();
        brandDomain.setBrandName("Adidas");
        brandDomain.setDescription("New Brand");

        when(brandRepository.findByBrandName("Adidas")).thenReturn(null);

        brandService.saveOrUpdateBrand(brandDomain);

        verify(brandRepository).findByBrandName("Adidas");



    }



    @Test
    void testDeleteBrand_whenBrandExists_shouldDeleteAndReturnSuccessMessage() {
        Integer brandId = 1;
        Brand brand = new Brand();
        brand.setBrandId(brandId);
        brand.setBrandName("Test Brand");

        when(brandRepository.findById(brandId)).thenReturn(Optional.of(brand));

        String result = brandService.deleteBrand(brandId);

        verify(brandRepository).deleteById(brandId);
        assertEquals("brand is deleted with Id :" + brandId, result);
    }
    @Test
    void testDeleteBrand_whenBrandNotFound_shouldReturnErrorMessage() {
        Integer brandId = 10;

        when(brandRepository.findById(brandId)).thenReturn(Optional.empty());

        String result = brandService.deleteBrand(brandId);

        verify(brandRepository, never()).deleteById(any());
        assertEquals("brand id not prasent :" + brandId, result);
    }

    @ParameterizedTest
    @ValueSource(ints ={1,2,3})
    void deleteBrands_With_multipleIds(Integer brandId){
        Brand brand = new Brand();
        brand.setBrandId(brandId);
        brand.setBrandName("Brand " + brandId);
        brand.setDescription("Test Brand");
        when(brandRepository.findById(brandId)).thenReturn(Optional.of(brand));
        String msg = brandService.deleteBrand(brandId);
        System.out.println(msg);
        verify(brandRepository).deleteById(brandId);
        assertEquals("brand is deleted with Id :" + brandId, msg);
    }
    @Test
    void getAllBrands(){
        Brand brand1 = new Brand();
        brand1.setBrandId(1);
        brand1.setBrandName("Nike");
        brand1.setDescription("Footwear");
        brand1.setcreatedDate(LocalDateTime.of(2024, 1, 10, 10, 0));

        Brand brand2 = new Brand();
        brand2.setBrandId(2);
        brand2.setBrandName("Adidas");
        brand2.setDescription("Sportswear");
        brand2.setcreatedDate(LocalDateTime.of(2024, 2, 15, 12, 0));

        List<Brand> mockBrands = Arrays.asList(brand1, brand2);
        when(brandRepository.findAll()).thenReturn(mockBrands);
        List<BrandDomain> allBrands = brandService.getAllBrands();

        assertEquals(2,mockBrands.size());

    }

}