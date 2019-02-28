package com.phenix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer () {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/buyer/*");
//                registry.addMapping("/buyer/product/*");
//                registry.addMapping("/buyer/product/list/*");
                registry.addMapping("/*")
                        .allowedOrigins("*")
                        .allowedHeaders("*")
                        .allowedMethods("*")
                        .allowCredentials(true);
            }
        };
    }

//    @Autowired
//    private StateMachine<OrderStates, OrderEvents> stateMachine;
//
//    @Override
//    public void run(String... args) {
//        stateMachine.start();
//        stateMachine.sendEvent(OrderEvents.PAY);
//        stateMachine.sendEvent(OrderEvents.RECEIVE);
//    }
}
