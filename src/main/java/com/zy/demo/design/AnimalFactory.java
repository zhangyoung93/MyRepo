package com.zy.demo.design;

/**
 * 简单工厂模式
 * @author zy
 */
public class AnimalFactory {

    /**
     * 公有静态方法生产派生类实例
     * @param name 派生类实例名称
     * @return 指定派生类实例
     */
    public static Animal getAnimal(String name){
        switch (name){
            case "cat":
                return new Cat();
            case "dog":
                return new Dog();
            default:
                return null;
        }
    }
}

/**
 * 基类——动物
 */
interface Animal{
    /**
     * 动物的进食行为
     */
    void eat();
}

/**
 * 派生类——狗
 */
class Dog implements Animal{

    /**
     * 狗的进食行为
     */
    @Override
    public void eat() {
        System.out.println("Dog eats food.");
    }
}

/**
 * 派生类——猫
 */
class Cat implements Animal{

    /**
     * 猫的进食行为
     */
    @Override
    public void eat() {
        System.out.println("Cat eats food.");
    }
}