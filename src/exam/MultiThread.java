package exam;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : qizidog
 * @date : 2021-04-18 11:55
 * @description :
 * 百度二面，多线程同步
 **/


public class MultiThread {
    public static void main(String[] args) {

        /*int [] arr = new int[20];
        for(int i=1; i<=20; i++){
            arr[i-1] = i;
        }
        MyArr myArr = new MyArr(arr);

        new Thread(()->{
            try {
                while (myArr.idx<myArr.arr.length) {
                    myArr.odd();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread-1").start();
        new Thread(()->{
            try {
                while (myArr.idx<myArr.arr.length) {
                    myArr.even();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread-2").start();*/


        /*Foo foo = new Foo();
        new Thread(()->{
            for (int i = 0; i < 12; i++) {
                foo.first();
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < 12; i++) {
                foo.second();
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < 12; i++) {
                foo.third();
            }
        }).start();*/


        FizzBuzz fizzBuzz = new FizzBuzz(15);
        new Thread(()->{
            try {
                while(fizzBuzz.i <= fizzBuzz.n)
                    fizzBuzz.fizz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                while(fizzBuzz.i <= fizzBuzz.n)
                    fizzBuzz.buzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                while(fizzBuzz.i <= fizzBuzz.n)
                    fizzBuzz.fizzbuzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                while(fizzBuzz.i <= fizzBuzz.n)
                    fizzBuzz.number();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }



}

class MyArr{
    int[] arr;
    int idx;
    ReentrantLock lock = new ReentrantLock();
    Condition con = lock.newCondition();

    public MyArr(int[] arr) {
        this.arr = arr;
        this.idx = 0;
    }

    public void even() throws InterruptedException {
        lock.lock();
        try {
            while(idx%2==0){
                con.await();
            }
            if (idx < arr.length)
                System.out.println(Thread.currentThread().getName()+" -> "+arr[idx++]);
            con.signal();
        }finally {
            lock.unlock();
        }
    }

    public void odd() throws InterruptedException {
        lock.lock();
        try {
            while (idx % 2 != 0) {
                con.await();
            }
            if (idx < arr.length)
                System.out.println(Thread.currentThread().getName() + " -> " + arr[idx++]);
            con.signal();
        }finally {
            lock.unlock();
        }
    }
}

class Foo {

    private volatile int count = 1;

    public Foo() {}

    public void first(/*Runnable printFirst*/){
        while (count != 1) {
            // waiting for the first job to be done.
        }
        // printFirst.run();
        System.out.println("first");
        count = 2;
    }

    public void second(/*Runnable printSecond*/){
        while (count != 2) {
            // waiting for the first job to be done.
        }
        // printSecond.run();
        System.out.println("second");
        count = 3;
    }

    public void third(/*Runnable printThird*/){
        while (count != 3) {
            // waiting for the second job to be done.
        }
        // printThird.run();
        System.out.println("third");
        count = 1;
    }
}


class FizzBuzz {
    int n;
    volatile int i = 1;

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public synchronized void fizz(/*Runnable printFizz*/) throws InterruptedException {
        while(i%3!=0){
            if (i > n) return;
            wait();
        }
        if(i%5!=0)
            System.out.println("fizz");
            // printFizz.run();
        notifyAll();
        i++;
    }

    // printBuzz.run() outputs "buzz".
    public synchronized void buzz(/*Runnable printBuzz*/) throws InterruptedException {
        while(i%5!=0){
            if (i > n) return;
            wait();
        }
        if(i%3!=0)
            System.out.println("buzz");
            // printBuzz.run();
        notifyAll();
        i++;
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public synchronized void fizzbuzz(/*Runnable printFizzBuzz*/) throws InterruptedException {
        while(i%3!=0 || i%5!=0){
            if (i > n) return;
            wait();
        }
        // printFizzBuzz.run();
        System.out.println("fizzbuzz");
        notifyAll();
        i++;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public synchronized void number(/*IntConsumer printNumber*/) throws InterruptedException {
        while(i%3==0 || i%5==0){
            if (i > n) return;
            wait();
        }
        // printNumber.accept(n);
        System.out.println(i);
        notifyAll();
        i++;
    }
}
