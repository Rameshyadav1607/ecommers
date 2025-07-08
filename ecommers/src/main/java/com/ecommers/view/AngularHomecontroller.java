package com.ecommers.view;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/angularHome")
public class AngularHomecontroller {
    @GetMapping("/welcome")
    public Map<String ,Object>  homepageview(){
        Map<String,Object>  mav=new HashMap<>();
        mav.put("view","home page");
        mav.put("applicatione name","sairam");
        mav.put("applicationType","ecommerces");

         return  mav;

    }

    @GetMapping("/loginPage")
    public Map<String,Object> loginPage(){
        Map<String ,Object> mav=new HashMap<>();
        mav.put("ecommerce name","Sairam");
        mav.put("view","login page");
        return  mav;
    }
}
