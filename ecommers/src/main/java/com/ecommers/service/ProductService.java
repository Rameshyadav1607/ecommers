package com.ecommers.service;


import com.ecommers.domain.ProductDomain;
import com.ecommers.model.Brand;
import com.ecommers.model.Category;
import com.ecommers.model.Colour;
import com.ecommers.model.Product;
import com.ecommers.repository.BrandRepository;
import com.ecommers.repository.CategoryRepository;
import com.ecommers.repository.ColourRepository;
import com.ecommers.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ColourRepository colourRepository;

    public String saveProductOrUpdate(ProductDomain productDomain) {
        Product product = productRepository.findByProductName(productDomain.getProductName());

        Category category = categoryRepository.findByCategoryName(productDomain.getCategoryName());
        Brand brand=brandRepository.findByBrandName(productDomain.getBrandName());

        if(product ==null){
            product=new Product();
            product.setcreatedDate(LocalDateTime.now());
        }else{
            product.setUpdateDate(LocalDateTime.now());
        }
        List<Colour> colour = colourRepository.findAllById(productDomain.getColourIds());

        product.setCategory(category);
        product.setBrand(brand);
        product.setProductName(productDomain.getProductName());
        product.setDescription(productDomain.getDescription());
        product.setPrice(productDomain.getPrice());
        product.setQuantiy(productDomain.getQuantity());
        product.setColours(new HashSet<>(colour));
        productRepository.save(product);

        return "product is inserted or updated";

    }

    public ProductDomain getProductByProductId(Integer productId) {

        Optional<Product> byId = productRepository.findById(productId);
        if(byId.isPresent()){
            Product    product=byId.get();
            ProductDomain domain=new ProductDomain();
            BeanUtils.copyProperties(product,domain);
            return domain;
        }
       return  null;

    }

    public List<ProductDomain> getAllProduct() {
        List<Product> all = productRepository.findAll();

        List<ProductDomain> productDomain = all.stream().map(product -> {
                    ProductDomain domain = new ProductDomain();
                    BeanUtils.copyProperties(product, domain);

                    domain.setQuantity(product.getQuantiy());
            if (product.getCategory()!=null) {
                domain.setCategoryName(product.getCategory().getCategoryName());
            }
            if(product.getBrand()!=null){
                domain.setBrandName(product.getBrand().getBrandName());
            }
            return domain;

        }

        ).collect(Collectors.toList());
        return productDomain;
    }

    public List<ProductDomain> getProductByName(String productName) {
       List<Product> allProducts= productRepository.findAllByProductName(productName.trim());

        List<ProductDomain> productDomains = allProducts.stream().map(
                product -> {
                    ProductDomain domain = new ProductDomain();
                    BeanUtils.copyProperties(product, domain);
                    return domain;
                }
        ).collect(Collectors.toList());
        return productDomains;
    }
}
