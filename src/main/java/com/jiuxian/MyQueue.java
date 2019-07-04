package com.jiuxian;

import java.util.Arrays;

/**
 * @author: liuzejun
 * *
 * @email: 857591294@qq.com
 * *
 * @date: 2019-07-04 22:40:33
 * *
 * @comment: 队列
 */
public class MyQueue {

    private int[] queue;
    private int front;
    private int rear;

    public MyQueue(int capacity) {
        this.queue = new int[capacity];
    }

    public void enQueue(int element) {
        if ((rear + 1) % this.queue.length == front) {
            System.err.println("队列已满。。。");
            return;
        }
        this.queue[rear] = element;
        rear = (rear + 1) % this.queue.length;
    }

    public int deQueue() {
        assert rear != front : "队列已空";

        int deQueueElement = queue[front];
        queue[front] = 0;
        front = (front + 1) % queue.length;
        return deQueueElement;
    }

    public void output() {
        System.out.println();
        Arrays.stream(this.queue).forEach(System.out::print);
        System.out.println();
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue(8);
        queue.enQueue(3);
        queue.enQueue(4);
        queue.enQueue(6);
        queue.enQueue(7);
        queue.enQueue(9);
        queue.output();

        System.out.print(queue.deQueue());
        System.out.print(queue.deQueue());
        System.out.print(queue.deQueue());
        System.out.print(queue.deQueue());
        queue.output();
    }
}