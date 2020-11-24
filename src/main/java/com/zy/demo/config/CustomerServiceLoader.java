package com.zy.demo.config;

import com.zy.demo.service.TestService;

import java.util.ServiceLoader;

/**
 * 自定义SPI
 * @author zy
 */
public class CustomerServiceLoader {
    public static void main(String[] args){
        //加载指定接口的ServiceLoader
        ServiceLoader<TestService> serviceLoader = ServiceLoader.load(TestService.class);
        //遍历接口实现类
        for(TestService testService : serviceLoader){
            //获取接口实现类名称
            System.out.println(testService.getClass().getName());
            //执行接口方法
            testService.doTest();
        }
    }
}
