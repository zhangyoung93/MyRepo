package com.zy.demo.config;

import com.zy.demo.service.TestService;

import java.util.ServiceLoader;

/**
 * 自定义SPI
 * @author zy
 */
public class CustomerServiceLoader {
    public static void main(String[] args){
        ServiceLoader<TestService> serviceLoader = ServiceLoader.load(TestService.class);
        for(TestService testService : serviceLoader){
            System.out.println(testService.getClass().getName());
            testService.doTest();
        }
    }
}
