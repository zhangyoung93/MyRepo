package com.zy.demo.service.impl;

import com.zy.demo.service.TestService;

public class TestServiceImpl implements TestService {
    @Override
    public void doTest() {
        System.out.println("TestServiceImpl-doTest");
    }
}
