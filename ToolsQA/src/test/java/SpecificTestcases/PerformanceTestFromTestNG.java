package SpecificTestcases;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PerformanceTestFromTestNG {

    @Test
    public void testPerformance1()
    {
        System.out.println("Test passed");
        Assert.assertTrue(true);
    }

    @Test
    public void testPerformance2()
    {
        System.out.println("Test failed");
        Assert.fail();
    }

    @Test
    public void test3()
    {
        System.out.println("Test passed");
        Assert.assertTrue(true);
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