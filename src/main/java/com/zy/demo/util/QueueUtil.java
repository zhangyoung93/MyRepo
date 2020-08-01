package com.zy.demo.util;

import java.util.Arrays;

/**
 * 队列
 * 1、顺序队列
 * 2、链式队列
 * 3、循环队列
 * @param <E> 元素
 */
public class QueueUtil<E> {

    /* **** 顺序队列----->start **** */

    //队列元素
    private Object[] objects;

    //队列元素个数
    private int size;

    //队列初始化默认值
    private static final Object[] DEFAULT_OBJECTS = {};

    //队列初始化默认容量
    private static int DEFAULT_CAPACITY = 10;

    /**
     * 队列的无参构造方法
     */
    public QueueUtil(){
        this.objects = DEFAULT_OBJECTS;
    }

    /**
     * 队列指定初始容量的构造方法
     * @param capacity 容量
     */
    public QueueUtil(int capacity){
        //校验输入容量
        if(capacity > 0){
            this.objects = new Object[capacity];
        }else if(capacity == 0){
            this.objects = DEFAULT_OBJECTS;
        }else{
            throw new IllegalArgumentException("Illegal capacity=" + capacity);
        }
    }

    /**
     * 获取队列元素个数
     * @return 队列元素个数
     */
    public int size(){
        return this.size;
    }

    /**
     * 按照从队头到队尾的顺序打印队列
     * @return 打印结果字符串，结果用"{}"包裹。
     */
    @Override
    public String toString() {
        return Arrays.toString(objects);
    }

    /**
     * 数组扩容
     * 时间复杂度：可能是O(n)。数组扩容底层调用的是native修饰的System.arraycopy()方法，是在JVM本地方法栈中执行的，源码是非Java语言实现的。
     * 空间复杂度：O(n)  --扩容操作会创建新数组对象，数组长度与输入数据线性相关。
     */
    private void expand(){
        //如果队列元素个数大于数组容量，则对数组扩容
        if(this.size + 1 > this.objects.length){
            //新数组长度的计算方法
            int newLen = this.objects.length >= DEFAULT_CAPACITY ? this.objects.length * 3/2 : DEFAULT_CAPACITY;
            //校验新数组长度
            if(newLen >= Integer.MAX_VALUE){
                throw new IllegalArgumentException("the length of array is too large:"+newLen);
            }
            //扩容
            this.objects = Arrays.copyOf(this.objects,newLen);
        }
    }

    /**
     * 入队
     * 时间复杂度：可能是O(n)。数组扩容底层调用的是native修饰的System.arraycopy()方法，是在JVM本地方法栈中执行的，源码是非Java语言实现的。
     * 空间复杂度：O(n)  --扩容操作会创建新数组对象，数组长度与输入数据线性相关。
     * @param e 元素
     * @return 入队成功则返回true；否则返回false。
     */
    public boolean add(E e){
        expand();
        //在最后一个队列元素后添加新元素，并计数。
        this.objects[this.size++] = e;
        return true;
    }

    /**
     * 出队
     * 时间复杂度：O(n) --数组起始索引的元素出队后，整体要重新排列各元素在数组中的位置。
     * 空间复杂度：O(1) --使用有限的内存资源。
     * @return 队头元素
     */
    @SuppressWarnings("unchecked")
    public E poll(){
        //空队列校验
        if(this.size == 0){
            return null;
        }
        //出队元素
        E e = null;
        //获取数组长度
        int arrLen = this.size;
        //遍历队列元素数组
        for(int i = 0 ; i < arrLen ; i++){
            //删除队头元素
            if(i == 0){
                e = (E)this.objects[i];
                this.objects[i] = null;
                //队列只有一个元素直接初始化数组
                if(this.size == 1){
                    this.objects = DEFAULT_OBJECTS;
                }
            }else{
                //将其它元素向队头重新分配索引
                this.objects[i-1] = this.objects[i];
                //队尾元素置空
                if(i == arrLen-1) {
                    this.objects[i] = null;
                }
            }
        }
        //遍历数组结束后再重新计数队列元素个数。
        this.size--;
        return e;
    }

    /**
     * 获取队头元素
     * 时间复杂度：O(1)  --数组根据索引查询的复杂度是O(1)
     * 空间复杂度：O(1) --使用有限的内存资源。
     * @return 队头元素
     */
    @SuppressWarnings("unchecked")
    public E peek(){
        //校验空队列
        if(this.size == 0){
            return null;
        }
        //数组第一个元素即队头元素
        return (E)this.objects[0];
    }

    /* **** 顺序队列----->end **** */
}
