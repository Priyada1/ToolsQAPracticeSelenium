package SpecificTestcases;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RetryTestFromTestNg {

    @Test
    public void test1() {
        System.out.println("Test 1");
        Assert.assertTrue(true);
    }

    @Test
    public void test2(){
        Assert.fail();
    }

    @Test
    public void test3() {
        System.out.println("Test 3");
        Assert.assertEquals(2,2);
    }
}
