package com.concurrent.thread;

/**
 * 旨在寻找两种创建线程方法的不同
 * @author haodong.zhao
 * @version 1.0. Added Time:2019/3/23 14:42
 */
public class CreateMethodDemo {

    /**
     * 计数器
     */
    static class Counter {
        private int count = 0;

        public void increment() {
            count++;
        }

        public int value() {
            return count;
        }
    }

    /**
     * 继承于Thread的子类
     */
    static class MyThread extends Thread {
        private Counter counter = new Counter();

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                counter.increment();
            }
            System.out.println("thread：" + counter.value());
        }
    }

    public static void main(String[] args) {
        // 直接创建线程
        Runnable task = new Runnable() {

            private Counter counter = new Counter();

            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    counter.increment();
                }
                System.out.println("task：" + counter.value());
            }
        };
        for (int i = 0; i < 4; i++) {
            new Thread(task).start();
        }

        // 以Thread子类创建线程
        for (int i = 0; i < 4; i++) {
            new MyThread().start();
        }
    }
}
