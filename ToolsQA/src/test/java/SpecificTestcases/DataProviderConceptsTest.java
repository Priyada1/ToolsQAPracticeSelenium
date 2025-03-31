package SpecificTestcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderConceptsTest {


    @DataProvider(name="data-provider")
    public Object[][] dpMethod(){
        return new Object[][] {{"name1",21}, {"name2",54}, {"name3",41}};
    }

    @Test(dataProvider = "data-provider",invocationCount = 5)
    public void testData(String name, Integer age){
        System.out.println("taking data from data provider: ");
        System.out.println("Name: " + name+ " Age: "+ age);

    }
}
