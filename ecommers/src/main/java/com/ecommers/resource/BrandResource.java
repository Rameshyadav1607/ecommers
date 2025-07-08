package com.ecommers.resource;

import com.ecommers.domain.BrandDomain;
import com.ecommers.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/brand")
public class BrandResource {

    @Autowired
    private BrandService brandService;

    @PostMapping("/save")
    public void saveOrUpdateBrand(@Validated @RequestBody BrandDomain brandDomain){
         brandService.saveOrUpdateBrand(brandDomain);
    }

    @DeleteMapping("/delete/{brandID}")
    public String deleteBrand(@PathVariable Integer brandID){

        return  brandService.deleteBrand(brandID);
    }

    @GetMapping("/all/brands")
    public List<BrandDomain> getAllBrands(){
      return   brandService.getAllBrands();

    }
    @GetMapping("/brand/names")
    public List<String> getAllBrandNames(){
        return brandService.getAllBrandNames();
    }


}
