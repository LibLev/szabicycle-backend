package com.szabicycle.szabicycle;

import com.szabicycle.szabicycle.entity.Product;
import com.szabicycle.szabicycle.repository.ProductRepository;
import com.szabicycle.szabicycle.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final ProductService productService;

    public DataInitializer(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {


        Product stateBicycl = Product.builder()
                .name("4130 RoadBike")
                .brand("State Bicycle Co.")
                .about("FRAME: STEEl \nWHEELS: ALUMINIUM 28\nGEARING: 44T - 11-28T\n SIZE: 55\n BRAKES: CALIPER\n")
                .price(150)
                .productPic(productService.createPic("photos/download.jpeg"))
                .build();
        productRepository.save(stateBicycl);

        Product willier = Product.builder()
                .name("4130 RoadBike")
                .brand("Willier")
                .about("FRAME: STEEl \nWHEELS: ALUMINIUM 28\nGEARING: 44T - 11-28T\n SIZE: 55\n BRAKES: CALIPER\n")
                .price(150)
                .productPic(productService.createPic("photos/willier.jpeg"))
                .build();
        productRepository.save(willier);

        Product cervelo = Product.builder()
                .name("4130 RoadBike")
                .brand("Willier")
                .about("FRAME: STEEl \nWHEELS: ALUMINIUM 28\nGEARING: 44T - 11-28T\n SIZE: 55\n BRAKES: CALIPER\n")
                .price(150)
                .productPic(productService.createPic("photos/cervelo.jpeg"))
                .build();
        productRepository.save(cervelo);

        Product colnago = Product.builder()
                .name("4130 RoadBike")
                .brand("Willier")
                .about("FRAME: STEEl \nWHEELS: ALUMINIUM 28\nGEARING: 44T - 11-28T\n SIZE: 55\n BRAKES: CALIPER\n")
                .price(150)
                .productPic(productService.createPic("photos/colnago.jpeg"))
                .build();
        productRepository.save(colnago);

        Product pinarello = Product.builder()
                .name("4130 RoadBike")
                .brand("Willier")
                .about("FRAME: STEEl \nWHEELS: ALUMINIUM 28\nGEARING: 44T - 11-28T\n SIZE: 55\n BRAKES: CALIPER\n")
                .price(150)
                .productPic(productService.createPic("photos/pinarello.jpeg"))
                .build();
        productRepository.save(pinarello);


    }
}
