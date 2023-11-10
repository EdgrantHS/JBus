package com.edgrantJBusRD;

import com.edgrantJBusRD.dbjson.JsonDBEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JBus {
    public static void main(String[] args) throws InterruptedException {
        JsonDBEngine.Run(JBus.class);
        SpringApplication.run(JBus.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> JsonDBEngine.join()));
//        System.out.println(MD5.encrypt("Hello"));
    }
}
