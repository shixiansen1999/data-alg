package com.sbx.data.queue;

import java.util.*;

/**
 * 使用数组实现队列
 */
public class QueueDemoByArray<E> {
    //队列的大小
    private int maxSize;
    //头指针
    private int head;
    //尾指针
    private int tail;
    //存放数据
    private Object[] arr;

    public QueueDemoByArray(int maxSize) {
        maxSize = maxSize;
        arr = new Object[maxSize];
        head = -1;
        tail = -1;
    }

    //判断队列是否满了
    public boolean isFull() {
        return tail == maxSize - 1;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return head == tail;
    }

    public int elements() {
        return tail - head;
    }

    //添加数据到队列
    public boolean add(E e) {
        if (isFull()) {
            throw new IllegalStateException("Queue full");
        }
        tail++;
        arr[tail] = e;
        return true;
    }

    //获取头部元素并删除
    public E remove() {
        E e = element();
        head++;
        return e;
    }

    public E element() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return (E) arr[head+1];
    }

    //显示队列所有数据
    public List<E> showAll() {
        if (isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        List<E> list = new ArrayList<>();
        for (int i = 0; i < elements(); i++) {
            list.add((E) arr[i]);
        }
        return list;
    }
}
