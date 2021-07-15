package ru.infinitycarwash.msorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication(scanBasePackages = {"ru.infinitycarwash"})
@EnableEurekaClient
@EnableFeignClients(basePackages = "ru.infinitycarwash.eurekafeign")
public class MsOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsOrderApplication.class, args);
    }

}
