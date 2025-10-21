package SpecificTestcases;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class ParallelSingleMethodTest {
    private static final Logger log = LoggerFactory.getLogger(ParallelSingleMethodTest.class);
    // Use AtomicInteger for a thread-safe counter
    private final AtomicInteger invocationCounter = new AtomicInteger(0);


    @Test(threadPoolSize =5 ,invocationCount = 10)
    public void processData() {
        int invocationNum = invocationCounter.incrementAndGet();
        long threadId = Thread.currentThread().getId();

        System.out.println("START :: Invocation {}/10 on thread: {}"+invocationNum+"and threadId: "+threadId);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("Thread was interrupted during invocation {} on thread: {}", invocationNum, threadId, e);
            Thread.currentThread().interrupt();
        }

        System.out.println("END :: Invocation {}/10 on thread: {}"+invocationNum+"and threadId: "+threadId);
        log.info("END   :: Invocation {}/10 on thread: {}", invocationNum, threadId);
    }
}