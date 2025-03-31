package SpecificTestcases;

import org.example.utilities.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RetryTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void test1() {
        System.out.println("Test 1");
        Assert.assertTrue(true);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void test2(){
        Assert.fail();
    }
}
