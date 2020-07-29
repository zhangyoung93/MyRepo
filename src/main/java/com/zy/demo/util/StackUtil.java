package com.zy.demo.util;

import java.util.Arrays;

/**
 * 栈
 * 1、通过数组实现顺序栈
 * 2、通过链表实现链式栈
 * @param <E> 栈内元素类型
 * @author zhangyoung93
 */
public class StackUtil<E> {

    /*****  通过链表实现链式栈----->start    *****/

    //栈顶
    private Node<E> first;
    //栈底
    private Node<E> last;
    //栈内的元素个数
    private int size;

    /**
     * 无参构造函数
     */
    public StackUtil(){

    }

    /**
     * 初始化栈顶的构造函数
     * @param e 元素
     */
    public StackUtil(E e){
        this.first = this.last = new Node<>(e,null);
        this.size++;
    }

    /**
     * 获取栈内元素的个数
     * @return 元素个数
     */
    public int size(){
        return this.size;
    }

    /**
     * 按照从栈顶到栈底的顺序打印栈内元素
     * 时间复杂度：O(n) --遍历一次栈
     * 空间复杂度：O(n) --新建StringBuilder对象存储打印结果，结果与输入数据量线性相关。
     * @return 打印栈内元素的结果
     */
    @Override
    public String toString() {
        //打印结果缓存
        StringBuilder sb = new StringBuilder();
        //获取栈顶元素
        Node<E> node = this.first;
        //打印结果通过"{}"包裹
        sb.append("{");
        //从栈顶向栈底遍历栈
        while (node != null){
            sb.append(node.e).append(",");
            node = node.next;
        }
        //最后一个逗号处理
        if (sb.toString().contains(",")) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     * 压栈
     * 时间复杂度：O(1) --链表头添加元素，只需修改一次指针。
     * 空间复杂度：O(1) --消耗有限的内存资源。
     * @param e 元素
     * @return 压栈成功返回true；否则返回false。
     */
    public boolean push(E e){
        //空栈时初始化栈顶和栈底。
        if(this.size == 0){
            //栈顶元素等于栈底元素
            this.first = this.last = new Node<>(e,null);
        }else{
            //栈内有元素时，把链表头作为栈顶，栈顶元素增删查的效率都很高。
            Node<E> firstNode = this.first;
            this.first = new Node<>(e,firstNode);
        }
        this.size++;
        return true;
    }

    /**
     * 出栈
     * 时间复杂度：O(1) --链表头查询与删除操作都是O(1)的复杂度
     * 空间复杂度：O(1) --消耗有限的内存资源。
     * @return 出栈的元素
     */
    public E pop(){
        //出栈元素
        E element;
        //空栈校验
        if(this.size == 0){
            return null;
        }else if(this.size == 1){
            //栈内只有1个元素时，出栈后将栈顶与栈底置为null。
            element = this.first.e;
            this.first = this.last = null;
        }else{
            //栈内有多个元素时，将栈顶元素出栈，并将下一个元素作为新栈顶。
            element = this.first.e;
            this.first = this.first.next;
        }
        this.size--;
        return element;
    }

    /**
     * 修改栈顶元素
     * 时间复杂度：O(1) --查询链表头结点的复杂度是O(1)
     * 空间复杂度：O(1) --消耗有限的内存资源
     * @param e 修改后的元素
     * @return 修改成功返回true；否则返回false。
     */
    public boolean modPop(E e){
        //空栈校验
        if(this.size == 0){
            return false;
        }
        //直接修改栈顶元素的数据域
        this.first.e = e;
        return true;
    }

    /**
     * 查找栈顶元素
     * 时间复杂度：O(1) --查询链表头结点的复杂度是O(1)
     * 空间复杂度：O(1) --消耗有限的内存资源。
     * @return 栈顶元素
     */
    public E getPop(){
        //空栈校验
        if(this.size == 0){
            return null;
        }
        //直接返回链表头结点数据域
        return this.first.e;
    }

    /**
     * 结点内部类
     */
    private static class Node<E>{
        //数据域
        private E e;
        //上个结点
        private Node<E> prev;
        //下个结点
        private Node<E> next;
        //结点构造函数
        Node(E e,Node<E> next){
            this.e = e;
            this.next = next;
        }
    }

    /*****  通过链表实现链式栈----->end    *****/

//    /*****  通过数组实现顺序栈----->start    *****/
//
//    //声明对象数组
//    private Object[] objects;
//
//    //栈的默认初始化结果
//    private static final Object[] DEFAULT_OBJECTS = {};
//
//    //栈的默认容量
//    private static final int DEFAULT_CAPACITY = 10;
//
//    //栈的元素个数
//    private int size;
//
//    //无参构造函数
//    public StackUtil(){
//        this.objects = DEFAULT_OBJECTS;
//    }
//
//    /**
//     * 指定初始容量的构造函数
//     * @param capacity 初始容量
//     */
//    public StackUtil(int capacity){
//        if(capacity > 0){
//            this.objects = new Object[capacity];
//        }else if(capacity == 0){
//            this.objects = DEFAULT_OBJECTS;
//        }else{
//            throw new IllegalArgumentException("Illegal Capacity:" + capacity);
//        }
//    }
//
//    /**
//     * 获取栈内的元素个数
//     * @return 栈内的元素个数
//     */
//    public int size(){
//        return this.size;
//    }
//
//    /**
//     * 校验规则
//     * @param index 数组索引
//     */
//    private void valid(int index){
//        //数组越界校验
//        if(index < 0 || this.size > 0 && index > this.size - 1){
//            throw new IndexOutOfBoundsException("Index:" + index);
//        }
//    }
//
//    /**
//     * 打印栈内元素
//     */
//    @Override
//    public String toString() {
//        return Arrays.toString(this.objects);
//    }
//
//    /**
//     * 压栈
//     * 时间复杂度：O(1) --数组尾部添加元素，直接通过索引赋值。
//     * 空间复杂度：O(n) --新建数组对象，数组大小与输入数据量n线性相关。
//     * @param e 元素
//     * @return 添加元素成功返回true；否则返回false。
//     */
//    public boolean push(E e){
//        //原数组中无元素，则新建默认容量的数组
//        if(this.objects == DEFAULT_OBJECTS){
//            this.objects = new Object[DEFAULT_CAPACITY];
//        }else{
//            //添加元素后数组长度大于数组容量，则扩容。
//            if(this.size + 1 > this.objects.length){
//                //先校验容量是否超过Integer的最大值，控制JVM内存使用量。
//                if(this.size + 1 >= Integer.MAX_VALUE){
//                    throw new OutOfMemoryError("The size of stack is too large!");
//                }
//                //扩容值规定：如果原数组长度小于默认容量，则扩容到默认容量；否则扩容到原数组长度的1.5倍。
//                int newLength = this.objects.length < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : (int) (1.5 * this.objects.length);
//                //重新实例化扩容后的数组
//                this.objects = Arrays.copyOf(this.objects, newLength);
//            }
//        }
//        //新添加的元素赋值到原数组尾部，不需要考虑索引的重排列。
//        this.objects[this.size++] = e;
//        return true;
//    }
//
//    /**
//     * 出栈
//     * 时间复杂度：O(1) --数组根据索引取值。
//     * 空间复杂度：O(1) --使用有限的变量资源。
//     * @return 出栈的元素
//     */
//    public E pop(){
//        //获取栈顶元素
//        E popElement = get(this.size-1);
//        //栈顶元素置为null
//        this.objects[--this.size] = null;
//        //size--可能会导致数组越界
//        return popElement;
//    }
//
//    /**
//     * 在栈中从栈底到栈顶查找指定索引处的元素
//     * 时间复杂度：O(1) --数组根据索引查找元素。
//     * 空间复杂度：O(1) --使用有限的变量资源。
//     * @param index 索引
//     * @return 元素
//     */
//    @SuppressWarnings("unchecked")
//    public E get(int index){
//        valid(index);
//        return (E)this.objects[index];
//    }
//
//    /**
//     * 修改栈顶元素
//     * 时间复杂度：O(1) --数组根据索引查找元素。
//     * 空间复杂度：O(1) --使用有限的变量资源。
//     * @param e 修改后的元素
//     * @return 修改成功返回true；否则返回false。
//     */
//    public boolean modPop(E e){
//        //注意不能对size做赋值。
//        this.objects[this.size-1] = e;
//        return true;
//    }
//
//    /*****  通过数组实现顺序栈----->end    *****/
}
