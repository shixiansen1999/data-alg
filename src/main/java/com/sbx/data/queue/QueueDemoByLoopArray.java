package com.sbx.data.queue;

import java.util.*;

public class QueueDemoByLoopArray<E> {
    private int size;
    private int head;
    private int tail;
    private Object[] arr;

    public QueueDemoByLoopArray(int size) {
        size = size;
        arr = new Object[size];
        head = tail = 0;
    }

    public boolean isFull() {
        return (tail + 1) % size == head;
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public int elements() {
        return (tail + size - head) % size;
    }

    public Boolean add(E e) {
        if (isFull()) {
            throw new IllegalStateException("Queue full");
        }
        arr[tail] = e;
        tail = (tail+1) % size;
        return true;
    }

    public E remove() {
        E e = element();
        head = (head+1) % size ;
        return e;
    }

    public E element() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return (E) arr[head];
    }

    public List<E> showAll() {
        if (isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        List<E> list = new ArrayList<>();
        for (int i = 0; i < elements(); i++) {
            list.add((E) arr[(head + i) % size]);
        }
        return list;
    }
}
