package SpecificTestcases;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RetryTestFromTestNg {

    @Test
    public void test32() {
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

    @Test
    public void flakyTest()
    {
        if(System.currentTimeMillis()%2==0)
        {
           System.out.println("Test Passed");
        }
        else{
            System.out.println("Test Failed");
            Assert.fail();
        }
    }
}