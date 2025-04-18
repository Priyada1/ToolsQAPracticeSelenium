package SpecificTestcases;

import org.testng.annotations.Test;

public class ParallelSingleMethodTest {


    @Test(threadPoolSize =5 ,invocationCount = 10)
    public void processData() {
        System.out.println("Processing data in thread: " + Thread.currentThread().getName());
        System.out.println("Processing data in thread: " + Thread.currentThread().getId());
        // Simulate some processing
        try {
            Thread.sleep(1000); // Simulate a delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Data processed in thread: " + Thread.currentThread().getName());
    }
}