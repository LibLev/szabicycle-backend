package com.szabicycle.szabicycle;

import com.szabicycle.szabicycle.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
public class SzabicycleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SzabicycleApplication.class, args);
    }

}
