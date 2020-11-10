package juc.atomic;

import com.google.common.collect.Lists;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author gaoxing
 * @Date 2020-06-11 11:38
 */
public class AtomicDemo {

    public static void main(String[] args) {

        AtomicInteger lock = new AtomicInteger();

        lock.set(100);

        System.out.println(lock.get());

        Lists.newArrayList();

    }

}
