package com.ecommers.service;


import com.ecommers.domain.BrandDomain;
import com.ecommers.model.Brand;
import com.ecommers.repository.BrandRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;

    public void saveOrUpdateBrand(BrandDomain brandDomain) {

        Brand brand= brandRepository.findByBrandName(brandDomain.getBrandName());
        if (brand != null) {
            BeanUtils.copyProperties(brandDomain, brand, "brandId", "createdDate","updatedDate");
            brand.setupdatedDate(LocalDateTime.now());
        } else {
            brand = new Brand();
            brand.setBrandName(brandDomain.getBrandName());
            brand.setDescription(brandDomain.getDescription());
            brand.setcreatedDate(LocalDateTime.now());
        }

        brandRepository.save(brand);

        System.out.println("brand saved");

    }



    public String deleteBrand(Integer brandID) {

        Optional<Brand> optionalBrand = brandRepository.findById(brandID);

        if(optionalBrand.isPresent()){
            brandRepository.deleteById(brandID);
            return "brand is deleted with Id :" +optionalBrand.get().getBrandId();

        }
        else{
            return "brand id not prasent :"+brandID;
        }

    }

    public List<BrandDomain> getAllBrands() {
        List<Brand> all = brandRepository.findAll();

        return all.stream().map(brand -> {
            BrandDomain dto = new BrandDomain();
            BeanUtils.copyProperties(brand, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    public List<String> getAllBrandNames() {

            return   brandRepository.findAllBrandNames();
    }
}
