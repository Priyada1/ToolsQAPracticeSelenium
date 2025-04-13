package SpecificTestcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderConceptsTest {


    @DataProvider(name="data-provider",parallel = true)
    public Object[][] dpMethod(){
        return new Object[][] {{"name1",21}, {"name2",54}, {"name3",41}};
    }

    @Test(dataProvider = "data-provider",invocationCount = 5,threadPoolSize =2)
    public void testData(String name, Integer age) throws InterruptedException {
        System.out.println("taking data from data provider: ");
        System.out.println("Name: " + name+ " Age: "+ age);
        System.out.println("Thread ID: " + Thread.currentThread().getId());
        //Thread.sleep(15000);
    }
    /*
    Analogy:
Imagine a car wash with 2 bays (threadPoolSize = 2). You have 15 cars to wash (invocationCount * number of data sets).
Only 2 cars can be washed simultaneously. However, the attendants might use different individual cleaning crews
(represented by different Thread IDs) throughout the process of washing all 15 cars.
     */
}